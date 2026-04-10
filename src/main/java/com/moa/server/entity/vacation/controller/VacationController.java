package com.moa.server.entity.vacation.controller;

import com.moa.server.entity.vacation.service.VacationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

// 결재관련

@RestController
// @RequestMapping("/")
@RequiredArgsConstructor
public class VacationController {

    private final VacationService vacationService;


}
