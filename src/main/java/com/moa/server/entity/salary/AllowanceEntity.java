package com.moa.server.entity.salary;

import com.moa.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "allowance")
@Getter
@Setter // DTO 역할을 위해 세터를 열어줍니다
@NoArgsConstructor // JPA와 DTO 처리를 위한 기본 생성자
@AllArgsConstructor
@Builder
public class AllowanceEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allowance_id")
    private Integer allowanceId;

    @Column(name = "allowance_cord")
    private String allowanceCord;

    @Column(name = "allowance_name")
    private String allowanceName;

    @Column(name = "allowance_is_use")
    private Integer allowanceIsUse;
}
