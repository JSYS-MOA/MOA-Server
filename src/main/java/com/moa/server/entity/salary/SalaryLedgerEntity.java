package com.moa.server.entity.salary;

import com.moa.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "salary_ledger")
@Getter
@Setter // DTO 역할을 위해 세터를 열어줍니다
@NoArgsConstructor // JPA와 DTO 처리를 위한 기본 생성자
@AllArgsConstructor
@Builder
public class SalaryLedgerEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salary_ledger_id")
    private Integer salaryLedgerId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "bank_transfer_id")
    private Integer bankTransferId;

    @Column(name = "salary_date")
    private LocalDateTime salaryDate;

    @Column(name = "salary_amount")
    private Long salaryAmount;
}
