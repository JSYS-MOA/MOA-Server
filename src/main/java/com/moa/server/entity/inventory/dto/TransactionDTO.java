package com.moa.server.entity.inventory.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class TransactionDTO {

    private Integer transactionId;

    private Integer vendorId;

    private Integer orderformId;

    private Integer salaryLedgerId;

    private Integer transactionNum;

    private String transactionType;

    private Integer transactionPrice;

    private String transactionMemo;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
