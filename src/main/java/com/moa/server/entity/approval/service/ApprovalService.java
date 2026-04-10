package com.moa.server.entity.approval.service;

import com.moa.server.entity.approval.ApprovaRepository;
import com.moa.server.entity.approval.ApprovalLineRepository;
import com.moa.server.entity.approval.DocumentRepository;
import com.moa.server.entity.user.UserRepository;
import lombok.RequiredArgsConstructor;
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

}
