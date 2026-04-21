package com.moa.server.entity.hr2.dto;

import com.moa.server.entity.vacation.BasicVacationEntity;
import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasicVacationDTO {

    private Integer basicVacationId;
    private Integer basicVacationDay;
    private Integer gradeId;
    private String gradeCord;
    private String gradeName;

    public BasicVacationDTO(BasicVacationEntity entity) {
        // entity에서 값을 꺼내서 DTO 필드에 채워넣기
        this.basicVacationId = entity.getBasicVacationId();
        this.basicVacationDay = entity.getBasicVacationDay();
        this.gradeId = entity.getGrade().getGradeId();
        this.gradeCord = entity.getGrade().getGradeCord();
        this.gradeName = entity.getGrade().getGradeName();
    }
    public BasicVacationEntity toEntity() {
        return BasicVacationEntity.builder()
                .basicVacationId(this.basicVacationId)
                .basicVacationDay(this.basicVacationDay)
                .grade(this.toEntity().getGrade())
                .build();
    }
}
