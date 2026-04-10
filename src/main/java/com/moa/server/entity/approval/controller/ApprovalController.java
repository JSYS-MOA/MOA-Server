package com.moa.server.entity.approval.controller;

import com.moa.server.entity.approval.service.ApprovalService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

// 결재관련

@RestController
// @RequestMapping("/")
@RequiredArgsConstructor
public class ApprovalController {

    private final ApprovalService approvalService;


}
