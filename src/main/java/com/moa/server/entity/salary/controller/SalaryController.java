package com.moa.server.entity.salary.controller;

import com.moa.server.entity.salary.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

// 결재관련

@RestController
// @RequestMapping("/")
@RequiredArgsConstructor
public class SalaryController {

    private final SalaryService salaryService;


}
