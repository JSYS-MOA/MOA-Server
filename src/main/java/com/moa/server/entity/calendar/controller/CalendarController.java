package com.moa.server.entity.calendar.controller;


import com.moa.server.entity.calendar.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

// 결재관련

@RestController
// @RequestMapping("/")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService  calendarService;


}
