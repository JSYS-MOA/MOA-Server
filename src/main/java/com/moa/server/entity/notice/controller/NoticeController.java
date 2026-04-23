package com.moa.server.entity.notice.controller;

import com.moa.server.entity.notice.dto.NoticeResponseDTO;
import com.moa.server.entity.notice.service.NoticeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@RestController
@RequestMapping("/api/main")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping("/notices")
    public ResponseEntity<List<NoticeResponseDTO>> getNotices(HttpSession session){
        return ResponseEntity.ok(noticeService.getNotices(session));
    }

    @GetMapping("/notices/{noticeId}")
    public ResponseEntity<NoticeResponseDTO> getNoticeInfo(@PathVariable Integer noticeId){
        return ResponseEntity.ok(noticeService.getNoticeInfo(noticeId));
    }

    @PostMapping("/notices")
    //파일하고 같이 보낼 때는
    public ResponseEntity<Void> saveNotice(
            @RequestParam String noticeTitle,
            @RequestParam String noticeContent,
            @RequestParam String noticeType,
            @RequestParam Boolean isNotice,
            @RequestParam (required = false)MultipartFile file,
            HttpSession session) throws IOException{

        noticeService.saveNotice(noticeTitle, noticeContent, noticeType, isNotice, file, session);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/notices/{noticeId}")
    public ResponseEntity<Void> updateNotice(
            @PathVariable Integer noticeId,
            @RequestParam String noticeTitle,
            @RequestParam String noticeContent,
            @RequestParam String noticeType,
            @RequestParam(required = false) MultipartFile file,
            HttpSession session) throws IOException {

        noticeService.updateNotice(noticeId, noticeTitle, noticeContent, noticeType, file, session);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/file/{fileName}")
    public ResponseEntity<Resource> viewFile(@PathVariable String fileName) throws Exception {

        Path filePath = Paths.get(uploadDir).resolve(fileName);
        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        String ext = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        MediaType mediaType;
        String disposition = switch (ext) {
            case "pdf" -> {
                mediaType = MediaType.APPLICATION_PDF;
                yield "inline";
            }
            case "png", "jpg", "jpeg" -> {
                mediaType = MediaType.IMAGE_PNG;
                yield "inline";
            }
            default -> {
                mediaType = MediaType.APPLICATION_OCTET_STREAM;
                yield "attachment";
            }
        };

        String originalName = fileName.substring(fileName.indexOf("_") + 1);
        String encodedName = URLEncoder.encode(originalName, StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        disposition + "; filename=\"" + encodedName + "\"")
                .contentType(mediaType)
                .body(resource);
    }

    @DeleteMapping("/notices/{noticeId}")
    public ResponseEntity<Void> deleteNotice(
            @PathVariable Integer noticeId,
            HttpSession session) {

        noticeService.deleteNotice(noticeId, session);
        return ResponseEntity.ok().build();
    }
}
