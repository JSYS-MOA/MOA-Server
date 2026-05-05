package com.moa.server.entity.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CertificatesCardUpdateDTO {
    private String employeeId;
    private String userName;

    private Integer roleId;

    private Integer departmentId;
    private String departmentName;
    private String departmentCord;

    private Integer gradeId;
    private String gradeName;
}