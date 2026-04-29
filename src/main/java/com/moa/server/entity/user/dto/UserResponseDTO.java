package com.moa.server.entity.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

//캘린더에서 공유자 선택할 때 필요
@Getter
@Builder
public class UserResponseDTO {
    private Integer userId;
    private String userName;
    private String departmentName;
}
