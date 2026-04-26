package com.moa.server.entity.approval.service;

import com.moa.server.entity.approval.ApprovaEntity;
import com.moa.server.entity.approval.ApprovaRepository;
import com.moa.server.entity.approval.ApprovalLineRepository;
import com.moa.server.entity.approval.DocumentRepository;
import com.moa.server.entity.approval.dto.ApprovaUserDTO;
import com.moa.server.entity.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
