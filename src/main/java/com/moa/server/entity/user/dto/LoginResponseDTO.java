package com.moa.server.entity.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponseDTO {

    private String userName;
    private String employeeId;
    private  Integer departmentId;
    private Integer roleId;
    private Integer userId;
}
