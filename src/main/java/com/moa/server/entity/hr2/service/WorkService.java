package com.moa.server.entity.hr2.service;

import com.moa.server.entity.hr2.dto.FilterDTO;
import com.moa.server.entity.hr2.dto.WorkDTO;
import com.moa.server.entity.salary.AllowanceEntity;
import com.moa.server.entity.salary.AllowanceRepository;
import com.moa.server.entity.user.UserEntity;
import com.moa.server.entity.user.UserRepository;
import com.moa.server.entity.vacation.WorkEntity;
import com.moa.server.entity.vacation.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkService {
    private final WorkRepository workRepository;
    private final UserRepository userRepository;
    private final AllowanceRepository allowanceRepository;

    // 1. 전체 조회
    @Transactional
    public Page<WorkDTO> getList(int page, int size, FilterDTO filterDTO) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("workDate").descending());
        return workRepository.findAllWithDetails(filterDTO.getStartDate(),filterDTO.getFinishDate(), filterDTO.getCategory(), filterDTO.getKeyword(), pageable)
                .map(WorkDTO::new);
    }

    // 2. 상세 조회
    @Transactional
    public WorkDTO getDetail(Integer workId) {
        WorkEntity work = workRepository.findById(workId)
                .orElseThrow(() -> new IllegalArgumentException("해당 기록이 없습니다. ID: " + workId));
        return new WorkDTO(work);
    }

    // 3. 등록 (Create)
    @Transactional
    public void register(WorkDTO dto) {
        // DTO의 사번(EmployeeId)으로 유저 검색
        UserEntity user = userRepository.findByEmployeeId(dto.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("미확인 유저입니다."));

        // DTO의 수당 이름(allowanceName)으로 수당 엔티티 검색
        AllowanceEntity allowance = null;
        if (dto.getAllowanceName() != null && !dto.getAllowanceName().isEmpty()) {
            // findByAllowanceName이 Optional을 반환한다고 가정 시 .orElse(null) 처리
            allowance = (AllowanceEntity) allowanceRepository.findByAllowanceName(dto.getAllowanceName())
                    .orElse(null);
        }

        WorkEntity work = WorkEntity.builder()
                .user(user)
                .allowance(allowance)
                .workDate(dto.getWorkDate())
                .workMemo(dto.getWorkMemo())
                .build();

        workRepository.save(work);
    }

    // 4. 수정 (Update)
    @Transactional
    public void modify(WorkDTO dto) {
        WorkEntity work = workRepository.findById(dto.getWorkId())
                .orElseThrow(() -> new IllegalArgumentException("해당 기록이 없습니다."));

        // 수당 변경 처리
        if (dto.getAllowanceName() != null && !dto.getAllowanceName().isEmpty()) {
            // 수정 시에도 이름을 기준으로 찾거나, 코드가 있다면 코드로 찾기
            AllowanceEntity allowance = allowanceRepository.findByAllowanceName(dto.getAllowanceName())
                    .orElse(null);
            work.setAllowance(allowance);
        } else {
            work.setAllowance(null); // 수당이 없으면 NULL로 변경
        }

        // 데이터 업데이트 (Dirty Checking으로 자동 반영)
        work.setWorkDate(dto.getWorkDate());
        work.setWorkMemo(dto.getWorkMemo());
    }

    // 5. 삭제 (Delete)
    @Transactional
    public void remove(Integer workId) {
        workRepository.deleteById(workId);
    }
}

