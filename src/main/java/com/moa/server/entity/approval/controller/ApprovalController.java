package com.moa.server.entity.approval.controller;

import com.moa.server.entity.approval.dto.ApprovaAddDTO;
import com.moa.server.entity.approval.dto.ApprovaLineCordMapDTO;
import com.moa.server.entity.approval.dto.ApprovaUserDTO;
import com.moa.server.entity.approval.service.ApprovalService;

import com.moa.server.entity.inventory.ProductEntity;
import com.moa.server.entity.inventory.dto.OrderFormPostDTO;
import com.moa.server.entity.inventory.dto.ProductCordMapDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return approvalService.getApproverList(approver, pageable);
    }

    // 결재라인 선택
    @GetMapping("/orders/select/approvaLine")
    public Page<?> getApprovaLineList ( @PageableDefault(page = 0, size = 10 )Pageable pageable) {
        return approvalService.getApprovaLineCord( pageable);
    }

    // 결재 요청 approvals
    @PostMapping("/approvals")
    public void postAddApprovals( @RequestBody ApprovaAddDTO dto) {
        approvalService.insertApprovals(dto);
    }

    // 미결재 결재 삭제
    @DeleteMapping("/approvals/{approvaId}")
    public void postAddOrderForm( @PathVariable("approvaId") Integer approvaId ) {
        approvalService.Deleteapprova(approvaId);
    }

    // 팀장 결제 내역 반려 / 결재 처리 approvalAct/{approva_id}
    @PatchMapping("/approvalAct/{approvaId}")
    public ResponseEntity<?> updateUserRole (@PathVariable("approvaId") Integer approvaId, @RequestParam("approvaStatus") String approvaStatus ) {
        int result = approvalService.updateApprovaStatus(approvaId , approvaStatus);
        return ResponseEntity.ok(result);
    }


    // 팀원 조회 teamMembers , 팀원 상세조회 teamMembers/{department_id} <= {user_id} 바꿔 , 인사 평가 추가 teamMembers/{user_id}

}