package com.moa.server.entity.approval.service;

import com.moa.server.entity.approval.*;
import com.moa.server.entity.approval.dto.ApprovaAddDTO;
import com.moa.server.entity.approval.dto.ApprovaLineCordMapDTO;
import com.moa.server.entity.approval.dto.ApprovaUserDTO;
import com.moa.server.entity.inventory.OrdererEntity;
import com.moa.server.entity.inventory.OrderformEntity;
import com.moa.server.entity.inventory.dto.OrderFormPostDTO;
import com.moa.server.entity.user.UserEntity;
import com.moa.server.entity.user.UserRepository;
import com.moa.server.entity.user.dto.TeamUserDTO;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// 결재관련

@Service
@Transactional
@RequiredArgsConstructor
public class ApprovalService {
    private final UserRepository userRepository;

    private final DocumentRepository documentRepository;
    private final ApprovalLineRepository approvalLineRepository;
    private final ApprovaRepository approvaRepository;

    // 내가 작성한 결재 목록
    public Page<ApprovaUserDTO> getApprovaUserList(Integer writer, Pageable pageable) {
        Page<ApprovaEntity> entityPage = approvaRepository.findByWriter(  writer,  pageable);

        return entityPage.map(ApprovaEntity::MapDTO);
    }

    // 결재 내역 상세 조회
    public Page<ApprovaUserDTO> getApprovaInfo( Integer approvaId ,Pageable pageable) {
        Page<ApprovaEntity> entityPage = approvaRepository.findByApprovaId( approvaId,  pageable);

        return entityPage.map(ApprovaEntity::MapDTO);
    }

    // 팀장용 결재 조회
    public Page<ApprovaUserDTO> getApproverList(Integer approver, Pageable pageable) {
        Page<ApprovaEntity> entityPage = approvaRepository.findByApprover(  approver,  pageable);

        return entityPage.map(ApprovaEntity::MapDTO);
    }

    // 결재라인 선택
    public Page<ApprovaLineCordMapDTO> getApprovaLineCord(Pageable pageable) {
        Page<ApprovalLineEntity> entityPage = approvalLineRepository.findAll(pageable);
        return entityPage.map(ApprovalLineEntity::MapDTO);
    }

    // 결재 요청 approvals
    public void insertApprovals(ApprovaAddDTO dto) {

        ApprovaEntity approval = ApprovaEntity.builder()
                .approvaTitle(dto.getApprovaTitle())
                .approvaContent(dto.getApprovaContent())
                .writer(dto.getWriter())
                .approvaKind(dto.getApprovaKind())
                .approvaStatus(dto.getApprovaStatus())
                .approvaMemu(dto.getApprovaMemu())
                .file(dto.getFile())
                .approver(dto.getApprover())
                .approvaDate(dto.getApprovaDate())
                .build();

        approvaRepository.save(approval);

    }

    //  미결재 결재 삭제
    @Transactional
    public void Deleteapprova(Integer approvaId) {
        approvaRepository.deleteByApprovaId(approvaId);
    }

    // 팀장 결제 내역 반려 / 결재 처리 approvalAct/{approva_id}
    public int updateApprovaStatus ( Integer approvaId, String approvaStatus ) {
        return approvaRepository.updateApprovaIdApprovaStatus( approvaId , approvaStatus);
    }


    //  adminservice  => 팀원 조회 teamMembers , 팀원 상세조회 teamMembers/{department_id} <= {user_id} 바꿔 , 인사 평가 추가 teamMembers/{user_id}

}
