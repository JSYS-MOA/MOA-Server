package com.moa.server.entity.inventory;

import com.moa.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "defect")
@Getter
@Setter // DTO 역할을 위해 세터를 열어줍니다
@NoArgsConstructor // JPA와 DTO 처리를 위한 기본 생성자
@AllArgsConstructor
@Builder
public class DefectEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "defect_id")
    private Integer defectId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "inventory_id")
    private Integer inventoryId;

    @Column(name = "defect_sno")
    private Integer defectSno;

    @Column(name = "defect_status")
    private String defectStatus;

    @Column(name = "req_date")
    private LocalDateTime reqDate;

    @Column(name = "disposal_date")
    private LocalDateTime disposalDate;

    @Column(name = "defect_memo")
    private String defectMemo;
}
