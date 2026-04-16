package com.moa.server.entity.user.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminUserDTO {


    private Integer roleId;
    private Integer userId;
    private String userName;
    private String employeeId;
    private String phone;
    private String email;

    // AdminRoleEntity에서 가져올 필드
    private String roleName;
    private String roleCode;

    // GradeEntity에서 가져올 필드
    private Integer gradeId;
    private String gradeCord;
    private String gradeName;

    // DepartmentEntity에서 가져올 필드
    private Integer departmentId;
    private String departmentCord;
    private String departmentName;




}
