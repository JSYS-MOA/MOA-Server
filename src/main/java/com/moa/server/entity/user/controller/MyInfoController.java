package com.moa.server.entity.user.controller;

import com.moa.server.entity.user.dto.MyInfoResponseDTO;
import com.moa.server.entity.user.service.MyInfoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/my")
@RequiredArgsConstructor
public class MyInfoController {
    private final MyInfoService myInfoService;

    @GetMapping("/profile")
    public ResponseEntity<MyInfoResponseDTO> getMyInfo(HttpSession session){
        return ResponseEntity.ok(myInfoService.getMyInfo(session));
    }
}
