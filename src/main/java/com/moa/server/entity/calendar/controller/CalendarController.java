package com.moa.server.entity.calendar.controller;


import com.moa.server.entity.calendar.dto.CalendarCategoryResponseDTO;
import com.moa.server.entity.calendar.dto.CalendarRequestDTO;
import com.moa.server.entity.calendar.dto.CalendarResponseDTO;
import com.moa.server.entity.calendar.service.CalendarService;
import com.moa.server.entity.user.dto.UserResponseDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/my/calendars")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService  calendarService;

    //캘린더 목록조회
    @GetMapping
    public ResponseEntity<List<CalendarResponseDTO>> getCalendars(
            @RequestParam(required = false) String type,
            HttpSession session) {
        return ResponseEntity.ok(calendarService.getCalendars(type, session));
    }
    //상세조회
    @GetMapping("/{calendarId}")
    public ResponseEntity<CalendarResponseDTO> getCalendar(
            @PathVariable Integer calendarId,
            HttpSession session) {
        return ResponseEntity.ok(calendarService.getCalendar(calendarId, session));
    }

    //일정구분 조회
    @GetMapping("/category")
    public ResponseEntity<List<CalendarCategoryResponseDTO>> getCategories() {
        return ResponseEntity.ok(calendarService.getCategories());
    }

    //공유자 조회(모달창에서)
    @GetMapping("/members")
    public ResponseEntity<List<UserResponseDTO>> getMembers(HttpSession session) {
        return ResponseEntity.ok(calendarService.getMembers(session));
    }

    //캘린더 등록
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Void> saveCalendar(
            @RequestPart("request") CalendarRequestDTO request,
            @RequestPart(value = "file", required = false) MultipartFile file,
            HttpSession session) throws IOException {
        calendarService.saveCalendar(request, file, session);
        return ResponseEntity.ok().build();
    }

    //캘린더 수정
    @PutMapping(value = "/{calendarId}", consumes = "multipart/form-data")
    public ResponseEntity<Void> updateCalendar(
            @PathVariable Integer calendarId,
            @RequestPart("request") CalendarRequestDTO request,
            @RequestPart(value = "file", required = false) MultipartFile file,
            HttpSession session) throws IOException {
        calendarService.updateCalendar(calendarId, request, file, session);
        return ResponseEntity.ok().build();
    }

    // 캘린더 삭제
    @DeleteMapping("/{calendarId}")
    public ResponseEntity<Void> deleteCalendar(
            @PathVariable Integer calendarId,
            HttpSession session) {
        calendarService.deleteCalendar(calendarId, session);
        return ResponseEntity.ok().build();
    }
}
