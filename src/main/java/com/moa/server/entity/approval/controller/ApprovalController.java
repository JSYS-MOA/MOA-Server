package com.moa.server.entity.approval.controller;

import com.moa.server.entity.approval.dto.ApprovaUserDTO;
import com.moa.server.entity.approval.service.ApprovalService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 결재관련

@RestController
@RequestMapping("/api/gw")
@RequiredArgsConstructor
public class ApprovalController {

    private final ApprovalService approvalService;

    @GetMapping("/approvals")
    public Page<ApprovaUserDTO> getApprovaUserList(@Param("writer") Integer writer, @Param("search") String search, @PageableDefault(page = 0, size = 10 )Pageable pageable) {
        return approvalService.getApprovaUserList(writer, pageable);
    }


}
