package com.moa.server.entity.user.dto;

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
    private String password;
    private String phone;
    private String email;

    // AdminRoleEntity에서 가져올 필드
    private String roleName;
    private String roleCode;




}
