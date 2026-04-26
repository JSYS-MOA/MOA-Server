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

import java.io.Writer;

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

    // 결재조회 / 팀장 결제 내역 상세조회
    @GetMapping("/approvals/{approvaId}")
    public Page<ApprovaUserDTO> getApprovaInfo( @PathVariable("approvaId") Integer approvaId , @PageableDefault(page = 0, size = 10 )Pageable pageable) {
        return  approvalService.getApprovaInfo( approvaId, pageable);
    }

    // 팀장 결제 내역 조회
    @GetMapping("/approvalWait")
    public Page<ApprovaUserDTO> getApproverList(@Param("approver") Integer approver, @Param("search") String search, @PageableDefault(page = 0, size = 10 )Pageable pageable) {
        return approvalService.getApprovaUserList(approver, pageable);
    }

    // 결재 요청 approvals

    // 미결재 결재 삭제 approvals/{approva_id}

    // 팀장 결제 내역 반려 / 결재 처리 approvalAct/{approva_id}

    // 팀원 조회 teamMembers

    // 팀원 조회 teamMembers/{department_id} <= {user_id} 바꿔

    // 인사 평가 추가 teamMembers/{user_id}


}