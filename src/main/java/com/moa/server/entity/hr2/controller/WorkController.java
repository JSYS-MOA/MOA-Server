package com.moa.server.entity.hr2.controller;

import com.moa.server.entity.hr2.dto.WorkDTO;
import com.moa.server.entity.hr2.service.WorkService;
import com.moa.server.entity.user.UserEntity;
import com.moa.server.entity.user.dto.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/hr/attendances")
@RequiredArgsConstructor
public class WorkController {
    private final WorkService service;



    //출근
    @PostMapping("/checkin")
    public ResponseEntity<Void> checkIn(HttpSession session) {
        SessionUser loginUser = (SessionUser) session.getAttribute(SessionUser.USER);
        service.checkIn(loginUser.getUserId());
        return ResponseEntity.ok().build();
    }

    // 퇴근
    @PostMapping("/checkout")
    public ResponseEntity<Void> checkOut(HttpSession session) {
        SessionUser loginUser = (SessionUser) session.getAttribute(SessionUser.USER);
        service.checkOut(loginUser.getUserId());
        return ResponseEntity.ok().build();
    }

    // 오늘 출퇴근 조회
    @GetMapping("/today")
    public WorkDTO today(HttpSession session) {
        SessionUser loginUser = (SessionUser) session.getAttribute(SessionUser.USER);
        return service.getTodayWork(loginUser.getUserId());
    }
}

