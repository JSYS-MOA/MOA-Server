package com.moa.server.entity.hr2.service;

import com.moa.server.entity.approval.ApprovaEntity;
import com.moa.server.entity.approval.ApprovaRepository;
import com.moa.server.entity.hr2.dto.ApprovalWaitDTO;
import com.moa.server.entity.hr2.dto.FilterDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApprovalWaitService {

    private final ApprovaRepository repository;

    @Transactional(readOnly = true)
    public Page<ApprovalWaitDTO> getList(int page, int size, FilterDTO filterDTO) {
        // 1. 페이징 설정
        Pageable pageable = PageRequest.of(page, size, Sort.by("approvaId").descending());

        // 2. 날짜 파싱 (안전한 파싱 메서드 사용)
        LocalDate startDate = filterDTO.getStartDate();
        LocalDate finishDate = filterDTO.getFinishDate();

        // 3. 문자열 필터 정제 (빈 문자열이나 "null" 문자열 방어)
        String category = cleanString(filterDTO.getCategory());
        String keyword = cleanString(filterDTO.getKeyword());
        Integer departmentId = filterDTO.getDepartmentId();

        // 4. 레포지토리 호출 및 DTO 변환
        // start는 00:00:00, finish는 23:59:59로 설정하여 해당 날짜 전체 포함
        LocalDateTime startDateTime = (startDate != null) ? startDate.atStartOfDay() : null;
        LocalDateTime finishDateTime = (finishDate != null) ? finishDate.atTime(23, 59, 59) : null;

        return repository.findApprovalList(
                        startDateTime,
                        finishDateTime,
                        category,
                        keyword,
                        departmentId,
                        pageable)
                .map(ApprovalWaitDTO::fromEntity);
    }

    @Transactional
    public void changeStatus(Integer approvaId, String approvaState) {
        ApprovaEntity entity = repository.findById(approvaId)
                .orElseThrow(() -> new RuntimeException("해당 결재 정보를 찾을 수 없습니다."));
        entity.setApprovaStatus(approvaState);
    }

    /**
     * 문자열 날짜를 안전하게 LocalDate로 변환
     */
    private LocalDate safeParseDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty() || "null".equalsIgnoreCase(dateStr)) {
            return null;
        }
        try {
            return LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            log.error("날짜 파싱 에러: {} - 형식이 올바르지 않습니다.", dateStr);
            return null; // 에러 발생 시 필터 적용 안 함
        }
    }

    /**
     * 빈 문자열이나 "null" 문자열을 null로 변환
     */
    private String cleanString(String str) {
        if (str == null || str.trim().isEmpty() || "null".equalsIgnoreCase(str)) {
            return null;
        }
        return str.trim();
    }
}