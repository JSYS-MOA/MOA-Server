package com.moa.server.entity.user.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MyInfoResponseDTO {

    private Integer userId;
    private String userName;
    private String employeeId;
    private String departmentName;
    private String gradeName;
    private String phone;
    private String email;
    private String address;
    private String bank;
    private String accountNum;
    private String startDate;
    private String birth;
    private String residentNumber;
    private Integer age;

}
