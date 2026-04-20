package com.moa.server.entity.notice.controller;

import com.moa.server.entity.notice.dto.NoticeResponseDTO;
import com.moa.server.entity.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
// @RequestMapping("/")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/notices")
    public ResponseEntity<List<NoticeResponseDTO>> getNotices(){
        return ResponseEntity.ok(noticeService.getNotices());
    }

}
