package com.moa.server.entity.inventory;

import com.moa.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transaction")
@Getter
@Setter // DTO 역할을 위해 세터를 열어줍니다
@NoArgsConstructor // JPA와 DTO 처리를 위한 기본 생성자
@AllArgsConstructor
@Builder
public class TransactionEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer transactionId;

    @Column(name = "vendor_id")
    private Integer vendorId;

    @Column(name = "orderform_id")
    private Integer orderformId;

    @Column(name = "salary_ledger_id")
    private Integer salaryLedgerId;

    @Column(name = "transaction_num")
    private Integer transactionNum;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "transaction_price")
    private Integer transactionPrice;

    @Column(name = "transaction_memo")
    private String transactionMemo;
}
