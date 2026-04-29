package com.moa.server.entity.hr2.service;

import com.moa.server.entity.approval.ApprovaEntity;
import com.moa.server.entity.approval.ApprovaRepository;
import com.moa.server.entity.hr2.dto.ApprovalWaitDTO;
import com.moa.server.entity.hr2.dto.FilterDTO;
import com.moa.server.entity.hr2.dto.WorkDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApprovalWaitService {

    private final ApprovaRepository repository;


    @Transactional
    public Page<ApprovalWaitDTO> getList(int page, int size, FilterDTO filterDTO) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("approvaId").descending());
        return repository.findApprovalList(filterDTO.getStartDate(),filterDTO.getFinishDate(), filterDTO.getCategory(), filterDTO.getKeyword(), pageable)
                .map(ApprovalWaitDTO::fromEntity);
    }

    @Transactional
    public void changeStatus(Integer approvaId, String approvaStatus) {
        // 1. 레포지토리에서 해당 결재 건 조회
        ApprovaEntity entity = repository.findById(approvaId)
                .orElseThrow(() -> new RuntimeException("해당 결재 정보를 찾을 수 없습니다."));

        // 2. 상태 변경 (select 박스에서 넘어온 값으로 세팅)
        entity.setApprovaStatus(approvaStatus);

        // Dirty Checking(변경 감지) 덕분에 save를 호출하지 않아도
        // 트랜잭션이 끝날 때 자동으로 DB에 UPDATE 쿼리가 날아갑니다.
    }

}
