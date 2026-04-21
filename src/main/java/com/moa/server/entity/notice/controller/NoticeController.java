package com.moa.server.entity.notice.controller;

import com.moa.server.entity.notice.dto.NoticeResponseDTO;
import com.moa.server.entity.notice.service.NoticeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/main")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/notices")
    public ResponseEntity<List<NoticeResponseDTO>> getNotices(){
        return ResponseEntity.ok(noticeService.getNotices());
    }

    @GetMapping("/notices/{noticeId}")
    public ResponseEntity<NoticeResponseDTO> getNoticeInfo(@PathVariable Integer noticeId){
        return ResponseEntity.ok(noticeService.getNoticeInfo(noticeId));
    }

    @PostMapping
    //파일하고 같이 보낼 때는
    public ResponseEntity<Void> saveNotice(
            @RequestParam String noticeTitle,
            @RequestParam String noticeContent,
            @RequestParam String noticeType,
            @RequestParam (required = false)MultipartFile file,
            HttpSession session) throws IOException{

        noticeService.saveNotice(noticeTitle, noticeContent, noticeType, file, session);
        return ResponseEntity.ok().build();
    }
}
