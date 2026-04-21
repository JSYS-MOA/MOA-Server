package com.moa.server.entity.hr2.service;


import com.moa.server.entity.hr2.dto.BasicVacationDTO;
import com.moa.server.entity.vacation.BasicVacationEntity;
import com.moa.server.entity.vacation.BasicVacationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BasicVacationService {

    private final BasicVacationRepository repository;
    // 1. 목록 조회: Entity 리스트를 가져와서 DTO 리스트로 변환해야 함
    public List<BasicVacationDTO> getList() {
        return repository.findAll().stream()
                .map(entity -> new BasicVacationDTO(entity)) // Entity를 DTO로 변환
                .toList();
    }

    // 2. 상세 조회: Entity를 찾은 뒤 DTO로 변환해서 반환
    public BasicVacationDTO getDetail(Integer basicVacationId) {
        BasicVacationEntity entity = repository.findById(basicVacationId).orElse(null);
        return entity != null ? new BasicVacationDTO(entity) : null;
    }

    // 3. 등록: DTO를 Entity로 변환해서 저장
    public void register(BasicVacationDTO data) {
        BasicVacationEntity entity = data.toEntity(); // DTO 내부에 toEntity() 메서드가 있다고 가정
        repository.save(entity);
    }

    // 4. 수정: 기존 Entity를 찾아서 DTO 데이터로 덮어쓰기
    public void modify(BasicVacationDTO data) {
        // 팁: 바로 save하기보다 기존 데이터를 조회한 뒤 변경하는 게 안전합니다.
        BasicVacationEntity entity = data.toEntity();
        repository.save(entity);
    }

    // 5. 삭제: 이건 ID값만 넘기니까 그대로 쓰셔도 됩니다.
    public void remove(Integer basicVacationId) {
        repository.deleteById(basicVacationId);
    }


}
