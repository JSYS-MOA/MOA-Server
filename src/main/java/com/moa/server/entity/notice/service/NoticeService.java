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

    //공지사항 리스트
    public List<NoticeResponseDTO> getNotices(){
        return noticeRepository.findAllByOrderByPostDateDesc()
                //stream -> list를 하나씩 꺼내서 NoticeEntitiy에서 NoticeResponseDTO로 변환
                .stream()
                .map(notice -> {
                        return NoticeResponseDTO.builder()
                        .noticeId(notice.getNoticeId())
                        .noticeTitle(notice.getNoticeTitle())
                        .file(notice.getFile())
                        .postDate(notice.getPostDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                        .writerName(getWriterName(notice.getWriter()))
                        .build();
                })
                .collect(Collectors.toList());
    }

    //공지사항 상세조회
    public NoticeResponseDTO getNoticeInfo(Integer noticeId){
        NoticeEntity notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return NoticeResponseDTO.builder()
                .noticeId(notice.getNoticeId())
                .noticeTitle(notice.getNoticeTitle())
                .noticeContent(notice.getNoticeContent())
                .file(notice.getFile())
                .postDate(notice.getPostDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                .writerName(getWriterName(notice.getWriter()))
                .build();
    }

    //공지사항 등록
    public void saveNotice(String noticeTitle, String noticeContent,
                           String noticeType, MultipartFile file,
                           HttpSession session) throws IOException {


        SessionUser loginUser = (SessionUser) session.getAttribute(SessionUser.USER);

        //파일 저장
        String fileName = null;
        if(file != null && !file.isEmpty()){
            //같은 파일명 덮어쓰기를 방지하기 위해 uuid라는 랜덤 고유 문자열을 사용
            fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path uploadPath = Paths.get(uploadDir);
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
    }

}
