package com.moa.server.entity.notice.service;

import com.moa.server.entity.notice.DocumentNoticeRepository;
import com.moa.server.entity.notice.NoticeRepository;
import com.moa.server.entity.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {
    private final UserRepository userRepository;

    private final NoticeRepository noticeRepository;
    private final DocumentNoticeRepository documentNoticeRepository;
}
