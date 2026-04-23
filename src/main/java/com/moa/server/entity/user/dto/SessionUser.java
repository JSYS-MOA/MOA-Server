package com.moa.server.entity.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class SessionUser implements Serializable {

    public static final String USER = "user";

    private String employeeId;
    private Integer departmentId;
    private Integer roleId;
    private  String userName;
    private  Integer userId;

}
