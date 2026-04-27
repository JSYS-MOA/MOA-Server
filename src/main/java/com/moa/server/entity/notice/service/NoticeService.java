package com.moa.server.entity.notice.service;

import com.moa.server.common.FileUtil;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;
    private final FileUtil fileUtil;
    private String getWriterName(Integer writerId) {
        return userRepository.findById(writerId)
                .map(UserEntity::getUserName)
                .orElse("알 수 없음");
    }

    private SessionUser getLoginUser(HttpSession session){
        SessionUser loginUser = (SessionUser) session.getAttribute(SessionUser.USER);

        if(loginUser == null){
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }
        return loginUser;
    }

    //공지사항 리스트
    public List<NoticeResponseDTO> getNotices(HttpSession session){
        SessionUser loginUser = getLoginUser(session);
        return noticeRepository.findAllByOrderByPostDateDesc()
                //stream -> list를 하나씩 꺼내서 NoticeEntity에서 NoticeResponseDTO로 변환
                .stream()
                .filter(notice -> {
                    if("전체".equals(notice.getNoticeType())) return true;
                    return userRepository.findById(notice.getWriter())
                            .map(user -> user.getDepartmentId().equals(loginUser.getDepartmentId()))
                            .orElse(false);
                })
                .map(notice -> NoticeResponseDTO.builder()
                        .noticeId(notice.getNoticeId())
                        .noticeTitle(notice.getNoticeTitle())
                        .file(notice.getFile())
                        .postDate(notice.getPostDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                        .writerName(getWriterName(notice.getWriter()))
                        .isNotice(notice.getIsNotice())
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
                           String noticeType,Boolean isNotice, MultipartFile file,
                           HttpSession session) throws IOException {


        SessionUser loginUser = getLoginUser(session);

        String fileName = null;
        if (file != null && !file.isEmpty()) {
            fileName = fileUtil.saveFile(file);
        }

        NoticeEntity notice = NoticeEntity.builder()
                .noticeTitle(noticeTitle)
                .noticeContent(noticeContent)
                .noticeType(noticeType)
                .isNotice(isNotice)
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

        final String fileName;

        if (file != null && !file.isEmpty()) {
            fileUtil.deleteFile(notice.getFile());
            fileName = fileUtil.saveFile(file);
        } else {
            fileName = notice.getFile();
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

        fileUtil.deleteFile(notice.getFile());
        noticeRepository.delete(notice);
    }
}