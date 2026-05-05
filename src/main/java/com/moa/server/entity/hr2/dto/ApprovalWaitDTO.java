package com.moa.server.entity.hr2.dto;

import com.moa.server.entity.approval.ApprovaEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApprovalWaitDTO {

    private Integer approvaId;
    private String approvaDate;
    private String approvaTitle;
    private String writer;
    private String documentName;
    private String approvaState;
    private String memo;
    private String departmentName; // 탭 구분
    private Integer departmentId;

    public static ApprovalWaitDTO fromEntity(ApprovaEntity entity) {

        // 1. ID를 기본값으로 설정 (String 변환)
        Integer deptIdStr = 0;

        if (entity.getUserWriter() != null && entity.getUserWriter().getDepartment() != null) {
            // 2. departmentId를 가져와서 문자열로 저장
            deptIdStr = (entity.getUserWriter().getDepartment().getDepartmentId());
        }

        return ApprovalWaitDTO.builder()
                .approvaId(entity.getApprovaId())
                .approvaDate(entity.getApprovaDate().toString())
                .approvaTitle(entity.getApprovaTitle())
                .writer(entity.getUserWriter().getUserName()) // User 조인 결과
                .documentName(entity.getLine().getDocumentName())
                .approvaState(entity.getApprovaStatus())
                .departmentName(entity.getDepartmentName())
                .departmentId(deptIdStr)
                .memo("")
                .build();
    }
}
