package com.moa.server.entity.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class HrCardAddRequestDTO {
    private Integer roleId;
    private Integer departmentId;
    private String departmentName;
    private Integer gradeId;
    private String gradeName;
}
