package com.moa.server.entity.notice.service;

import com.moa.server.entity.notice.DocumentNoticeRepository;
import com.moa.server.entity.notice.NoticeRepository;
import com.moa.server.entity.notice.dto.NoticeResponseDTO;
import com.moa.server.entity.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public List<NoticeResponseDTO> getNotices(){
        return noticeRepository.findAllByOrderByPostDateDesc()
                //stream -> list를 하나씩 꺼내서 NoticeEntitiy에서 NoticeResponseDTO로 변환
                .stream()
                .map(notice -> NoticeResponseDTO.builder()
                        .noticeId(notice.getNoticeId())
                        .noticeTitle(notice.getNoticeTitle())
                        .file(notice.getFile())
                        .postDate(notice.getPostDate())
                        .writer(notice.getWriter())
                        .build())
                .collect(Collectors.toList());
    }


}
