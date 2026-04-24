package com.moa.server.entity.approval.service;

import com.moa.server.entity.approval.ApprovaEntity;
import com.moa.server.entity.approval.ApprovaRepository;
import com.moa.server.entity.approval.ApprovalLineRepository;
import com.moa.server.entity.approval.DocumentRepository;
import com.moa.server.entity.approval.dto.ApprovaUserDTO;
import com.moa.server.entity.inventory.InventoryEntity;
import com.moa.server.entity.inventory.dto.InventoryDTO;
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

    public Page<ApprovaUserDTO> getApprovaUserList(Integer writer, Pageable pageable) {
        Page<ApprovaEntity> entityPage = approvaRepository.findByWriter(  writer,  pageable);

        return entityPage.map(ApprovaEntity::MapDTO);
    }


}
