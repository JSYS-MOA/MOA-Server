package com.moa.server.entity.approval.dto;

import com.moa.server.entity.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApprovaUserDTO {

    private Integer approvaId;
    private String approvaTitle;
    private String approvaContent;
    private Integer approvaKind;
    private String approvaStatus;
    private String approvaMemu;
    private String file;
    private LocalDateTime approvaDate;

    private UserInfo writerInfo;
    private ApproverInfo approverInfo;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserInfo {
        private Integer userId;
        private String userName;
        private String employeeId;
        private String phone;
        private String email;

        private String roleName;
        private String roleCode;

        private String gradeName;
        private String gradeCord;

        private String departmentName;
        private String departmentCord;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ApproverInfo {

        private Integer approvalLineId;
        private Integer approvalLineUser;
        private String approvalLineName;
        private Integer userId;
        private String userName;
        private String employeeId;
        private String phone;
        private String email;

        private String roleName;
        private String roleCode;

        private String gradeName;
        private String gradeCord;

        private String departmentName;
        private String departmentCord;
    }




}
