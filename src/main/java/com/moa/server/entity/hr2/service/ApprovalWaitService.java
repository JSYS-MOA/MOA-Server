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

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ApprovalWaitService {

    private final ApprovaRepository repository;


    @Transactional
    public Page<ApprovalWaitDTO> getList(int page, int size, FilterDTO filterDTO) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("approvaId").descending());

        LocalDateTime startDateTime = null;
        if (filterDTO.getStartDate() != null && !filterDTO.getStartDate().trim().isEmpty()) {
            try {
                startDateTime = LocalDate.parse(filterDTO.getStartDate()).atStartOfDay();
            } catch (Exception e) {
                System.out.println("시작 날짜 파싱 실패: " + filterDTO.getStartDate());
            }
        }

        LocalDateTime finishDateTime = null;
        if (filterDTO.getFinishDate() != null && !filterDTO.getFinishDate().trim().isEmpty()) {
            try {
                finishDateTime = LocalDate.parse(filterDTO.getFinishDate()).atTime(23, 59, 59);
            } catch (Exception e) {
                System.out.println("종료 날짜 파싱 실패: " + filterDTO.getFinishDate());
            }
        }

        // 2. 나머지 문자열 파라미터 처리 (빈 문자열이면 null로 처리해서 쿼리 조건 무시)
        String category = (filterDTO.getCategory() != null && !filterDTO.getCategory().trim().isEmpty())
                ? filterDTO.getCategory() : null;

        String keyword = (filterDTO.getKeyword() != null && !filterDTO.getKeyword().trim().isEmpty())
                ? filterDTO.getKeyword() : null;

        // 서비스 레이어의 getList 메서드 내부
        Integer departmentId = filterDTO.getDepartmentId();

        // 추가: 전체 조회(id가 0이거나 null일 때)를 위해 null로 보정
            if (departmentId == null || departmentId == 0) {
                departmentId = null;
            }

        System.out.println("최종 쿼리에 사용될 부서ID: " + departmentId);

        // 3. 레포지토리 호출
        return repository.findApprovalList(
                        startDateTime,
                        finishDateTime,
                        category,
                        keyword,
                        departmentId,
                        pageable)
                .map(ApprovalWaitDTO::fromEntity);



//        Pageable pageable = PageRequest.of(page, size, Sort.by("approvaId").descending());
//
//        LocalDate start = (filterDTO.getStartDate() != null && !filterDTO.getStartDate().isEmpty())
//                ? LocalDate.parse(filterDTO.getStartDate())
//                : null;
//
//        LocalDate finish = (filterDTO.getFinishDate() != null && !filterDTO.getFinishDate().isEmpty())
//                ? LocalDate.parse(filterDTO.getFinishDate())
//                : null;
//        String category = (filterDTO.getCategory() != null && !filterDTO.getCategory().isEmpty())
//                ? filterDTO.getCategory() : null;
//
//        String keyword = (filterDTO.getKeyword() != null && !filterDTO.getKeyword().isEmpty())
//                ? filterDTO.getKeyword() : null;
//
//        Integer departmentId = filterDTO.getDepartmentId();
//
//        System.out.println("백엔드 전달 부서명: " + departmentId);
//        return repository.findApprovalList(
//                        start != null ? start.atStartOfDay() : null,
//                        finish != null ? finish.atTime(23, 59, 59) : null,
//                        category,
//                        keyword,
//                        departmentId,
//                        pageable)
//                .map(ApprovalWaitDTO::fromEntity);
    }

    @Transactional // 리턴 안 해도 값이 넘어감
    public void changeStatus(Integer approvaId, String approvaState) {
        // 1. 레포지토리에서 해당 결재 건 조회
        ApprovaEntity entity = repository.findById(approvaId)
                .orElseThrow(() -> new RuntimeException("해당 결재 정보를 찾을 수 없습니다."));

        // 2. 상태 변경 (select 박스에서 넘어온 값으로 세팅)
        entity.setApprovaStatus(approvaState);

    }

}
