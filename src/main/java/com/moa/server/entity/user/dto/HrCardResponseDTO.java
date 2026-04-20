package com.moa.server.entity.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class HrCardResponseDTO {
    private Integer userId;
    private String userName;
    private String employeeId;
    private Integer roleId;
    private String phone;
    private String email;
    private String address;
    private LocalDate startDate;
    private LocalDate quitDate;
    private Integer departmentId;
    private String departmentName;
    private Integer gradeId;
    private String gradeName;
    private LocalDate birth;
    private String performance;
    private String profileUrl;
    private String bank;
    private String accountNum;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
