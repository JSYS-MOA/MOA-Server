package com.moa.server.entity.vacation;

import com.moa.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "basic_vacation")
@Getter
@Setter // DTO 역할을 위해 세터를 열어줍니다
@NoArgsConstructor // JPA와 DTO 처리를 위한 기본 생성자
@AllArgsConstructor
@Builder
public class BasicVacationEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "basic_vacation_id")
    private Integer basicVacationId;

    @Column(name = "basic_vacation_day")
    private Integer basicVacationDay;

    @Column(name = "grade_id")
    private Integer gradeId;
}
