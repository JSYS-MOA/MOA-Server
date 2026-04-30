package com.moa.server.entity.hr2.service;

import com.moa.server.entity.hr2.dto.FilterDTO;
import com.moa.server.entity.hr2.dto.VacationPrintDTO;
import com.moa.server.entity.vacation.VacationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VacationPrintService {
    private final VacationRepository repository;

    @Transactional
    public Page<VacationPrintDTO> getList(int page, int size, FilterDTO filterDTO) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("vacationId").descending());
        return repository.findVacationPrint(
                filterDTO.getStartDate(),
                filterDTO.getFinishDate(),
                filterDTO.getCategory(),
                filterDTO.getKeyword(),
                pageable
        ).map(VacationPrintDTO::fromEntity);
    }
    @Transactional
    public VacationPrintDTO getDetail(Integer vacationId) {
        return repository.findById(vacationId)
                .map(VacationPrintDTO::fromEntity)
                .orElseThrow(() -> new IllegalArgumentException("해당 기록이 없습니다. ID: " + vacationId));
    }


}
