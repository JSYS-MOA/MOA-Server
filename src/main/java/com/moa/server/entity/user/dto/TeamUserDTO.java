package com.moa.server.entity.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamUserDTO {

    private Integer userId;
    private String userName; //
    private String employeeId; //
    private String phone;
    private String email;

    private LocalDate startDate; //

    // 인사평가
    private String performance; //

    // AdminRoleEntity에서 가져올 필드
    private Integer roleId;
    private String roleName; //
    private String roleCode;

    // GradeEntity에서 가져올 필드
    private Integer gradeId;
    private String gradeCord;
    private String gradeName;

    // DepartmentEntity에서 가져올 필드
    private Integer departmentId;
    private String departmentCord;
    private String departmentName; //




}
