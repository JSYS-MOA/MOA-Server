package com.moa.server.entity.inventory.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    private Integer userId;
    private String userName;
    private String employeeId;
    private Integer departmentId;
    private String departmentName;
    private Integer gradeId;
    private String gradeName;
    private Long basePay;
    private Integer bankTransferId;
    private LocalDateTime salaryDate;
    private Long salaryAmount;
    private Long overtimeAllowance;
    private Long weekendAllowance;
    private Long annualAllowance;
}