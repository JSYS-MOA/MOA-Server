package com.moa.server.entity.hr2.dto;


import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilterKeywordDTO {
    private String userName;
    private String employeeId;
    private String departmentName;
    private String departmentCode;
    private int approvaKind;
    private String approvaKindName; // 구분 관련 컬럼이 없어 임의로 바구니를 만듦(ex,휴가/병가,법인카드,야근,주말근무 등)
}
