package com.moa.server.entity.hr2.service;


import com.moa.server.entity.hr2.dto.BasicVacationDTO;
import com.moa.server.entity.user.GradeEntity;
import com.moa.server.entity.user.GradeRepository;
import com.moa.server.entity.user.UserRepository;
import com.moa.server.entity.vacation.BasicVacationEntity;
import com.moa.server.entity.vacation.BasicVacationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BasicVacationService {

    private final BasicVacationRepository repository;
    private final GradeRepository gradeRepository;
    private final UserRepository userRepository;

    // Entity 를 가져와서 DTO 로 변환해야 함
    public List<BasicVacationDTO> getList() {
        return repository.findAll().stream()
                .map(entity -> new BasicVacationDTO(entity)) // Entity를 DTO로 변환
                .toList();
    }

    public BasicVacationDTO getDetail(Integer basicVacationId) {
        BasicVacationEntity entity = repository.findById(basicVacationId).orElse(null);
        return entity != null ? new BasicVacationDTO(entity) : null;
    }

    public void register(BasicVacationDTO data) {
        BasicVacationEntity entity = data.toEntity();
        GradeEntity grade = gradeRepository.findByGradeCord(data.getGradeCord())
                .orElseGet(() -> {
                    GradeEntity newGrade = new GradeEntity();
                    newGrade.setGradeCord(data.getGradeCord());
                    newGrade.setGradeName(data.getGradeName());
                    return gradeRepository.save(newGrade);
                });

        entity.setGrade(grade);
        repository.save(entity);
    }

    public void modify(BasicVacationDTO data) {
        //무결성확인
        BasicVacationEntity entity = repository.findById(data.getBasicVacationId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 연차 설정입니다."));
        GradeEntity grade = entity.getGrade();
        if (grade == null) {
            throw new IllegalArgumentException("연결된 직급 정보가 없습니다.");
        }
        //직급 업데이트
        grade.setGradeName(data.getGradeName());
        gradeRepository.save(grade);

        //연차일수 업데이트
        entity.setBasicVacationDay(data.getBasicVacationDay());
        repository.save(entity);
    }

    public void remove(Integer basicVacationId) {

        BasicVacationEntity vacation = repository.findById(basicVacationId)
                .orElseThrow(() -> new IllegalArgumentException("삭제할 데이터를 찾을 수 없습니다."));

        GradeEntity grade = vacation.getGrade();
        if (grade != null && userRepository.existsByGradeId(grade.getGradeId())) {
            throw new RuntimeException("삭제 불가: 해당 직급 사용 중입니다");
        }

        repository.deleteById(basicVacationId);
        // 연차 설정이 지워진 후 직급도 삭제
        if(grade!=null) {
            gradeRepository.delete(grade);
        }
    }


}
