package com.moa.server.entity.notice.service;

import com.moa.server.common.exception.CustomException;
import com.moa.server.common.exception.ErrorCode;
import com.moa.server.entity.notice.NoticeEntity;
import com.moa.server.entity.notice.NoticeRepository;
import com.moa.server.entity.notice.dto.NoticeResponseDTO;
import com.moa.server.entity.user.UserEntity;
import com.moa.server.entity.user.UserRepository;
import com.moa.server.entity.user.dto.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;
    private String getWriterName(Integer writerId) {
        return userRepository.findById(writerId)
                .map(UserEntity::getUserName)
                .orElse("알 수 없음");
    }
    @Value("${file.upload-dir}")
    private String uploadDir;

    private SessionUser getLoginUser(HttpSession session){
        SessionUser loginUser = (SessionUser) session.getAttribute(SessionUser.USER);

        if(loginUser == null){
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }
        return loginUser;
    }

    //공지사항 리스트
    public List<NoticeResponseDTO> getNotices(){
        return noticeRepository.findAllByOrderByPostDateDesc()
                //stream -> list를 하나씩 꺼내서 NoticeEntity에서 NoticeResponseDTO로 변환
                .stream()
                .map(notice -> NoticeResponseDTO.builder()
                        .noticeId(notice.getNoticeId())
                        .noticeTitle(notice.getNoticeTitle())
                        .file(notice.getFile())
                        .postDate(notice.getPostDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                        .writerName(getWriterName(notice.getWriter()))
                        .build()
                )
                .collect(Collectors.toList());
    }

    //공지사항 상세조회
    public NoticeResponseDTO getNoticeInfo(Integer noticeId){
        NoticeEntity notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOTICE_NOT_FOUND));

        return NoticeResponseDTO.builder()
                .noticeId(notice.getNoticeId())
                .noticeTitle(notice.getNoticeTitle())
                .noticeContent(notice.getNoticeContent())
                .file(notice.getFile())
                .postDate(notice.getPostDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                .writerName(getWriterName(notice.getWriter()))
                .writerId(notice.getWriter())
                .build();
    }

    //공지사항 등록
    public void saveNotice(String noticeTitle, String noticeContent,
                           String noticeType, MultipartFile file,
                           HttpSession session) throws IOException {


        SessionUser loginUser = getLoginUser(session);

        //파일 저장
        String fileName = null;
        if(file != null && !file.isEmpty()){
            //같은 파일명 덮어쓰기를 방지하기 위해 uuid라는 랜덤 고유 문자열을 사용
            fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            //업로드 경로를 path 객체로 변환(문자열 -> 객체)
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
                //폴더가 없을 때 자동으로 만들어줌(서버 컴퓨터에 저장을 위한 폴더생성)
            }
            //파일 저장
            Files.copy(file.getInputStream(), uploadPath.resolve(fileName));
        }

        NoticeEntity notice = NoticeEntity.builder()
                .noticeTitle(noticeTitle)
                .noticeContent(noticeContent)
                .noticeType(noticeType)
                .writer(loginUser.getUserId())
                .file(fileName)
                .postDate(LocalDateTime.now())
                .build();

        noticeRepository.save(notice);
    }

    //공지사항 수정
    public void updateNotice(Integer noticeId, String noticeTitle,
                             String noticeContent, String noticeType,
                             MultipartFile file, HttpSession session) throws IOException{

        SessionUser loginUser = getLoginUser(session);

        NoticeEntity notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOTICE_NOT_FOUND));

        if(!notice.getWriter().equals(loginUser.getUserId())){
            throw new CustomException(ErrorCode.FORBIDDEN);
        }

        String fileName = notice.getFile();

        if(file != null && !file.isEmpty()) {

            fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Files.copy(
                    file.getInputStream(),
                    uploadPath.resolve(fileName),
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING
            );

            if(notice.getFile() != null){
                Path oldFile = Paths.get(uploadDir).resolve(notice.getFile());
                Files.deleteIfExists(oldFile);
            }
        }

        notice.setNoticeTitle(noticeTitle);
        notice.setNoticeContent(noticeContent);
        notice.setNoticeType(noticeType);
        notice.setFile(fileName);
        notice.setUpdateDate(LocalDateTime.now());
    }

    //공지사항 삭제
    public void deleteNotice(Integer noticeId, HttpSession session){

        SessionUser loginUser = getLoginUser(session);

        NoticeEntity notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOTICE_NOT_FOUND));

        if(!notice.getWriter().equals(loginUser.getUserId())){
            throw new CustomException(ErrorCode.FORBIDDEN);
        }

        if(notice.getFile() != null){
            Path oldFile = Paths.get(uploadDir).resolve(notice.getFile());
            try {
                Files.deleteIfExists(oldFile);
            }catch(IOException e){
                System.out.println("파일 삭제 실패");
            }
        }
        noticeRepository.delete(notice);
    }
}