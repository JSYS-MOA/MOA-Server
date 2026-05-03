package com.moa.server.entity.inventory.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class TransactionSalaryRequestDTO {

    @JsonAlias("transaction_id")
    private Integer transactionId;

    @JsonAlias("user_id")
    private Integer userId;

    private Integer vendorId;

    @JsonAlias("salary_ledger_id")
    private Integer salaryLedgerId;

    @JsonAlias("transaction_num")
    private Integer transactionNum;

    @JsonAlias("transaction_type")
    private String transactionType;

    @JsonAlias("transaction_price")
    private Integer transactionPrice;

    @JsonAlias("transaction_memo")
    private String transactionMemo;

    @JsonAlias("bank_transfer_id")
    private Integer bankTransferId;

    @JsonAlias("salary_date")
    private LocalDateTime salaryDate;

    @JsonAlias("salary_amount")
    private Long salaryAmount;

    @JsonAlias("created_at")
    private LocalDateTime createdAt;

    @JsonAlias("updated_at")
    private LocalDateTime updatedAt;
}
