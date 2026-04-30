package com.moa.server.entity.inventory.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class TransactionSalaryDTO {

    private Integer transactionId;

    private Integer vendorId;

    private Integer salaryLedgerId;

    private Integer transactionNum;

    private String transactionType;

    private Integer transactionPrice;

    private String transactionMemo;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
