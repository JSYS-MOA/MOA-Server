package com.moa.server.entity.notice.controller;

import com.moa.server.entity.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

// 결재관련

@RestController
// @RequestMapping("/")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;


}
