package com.moa.server.entity.hr2.dto;

import com.moa.server.entity.approval.ApprovaEntity;
import lombok.*;

import java.util.Optional;

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
    private String departmentName;
    private Integer departmentId;

    public static ApprovalWaitDTO fromEntity(ApprovaEntity entity) {
        if (entity == null) return null;

        // 1. 부서 ID 안전하게 추출
        Integer deptId = 0;
        if (entity.getUserWriter() != null && entity.getUserWriter().getDepartment() != null) {
            deptId = entity.getUserWriter().getDepartment().getDepartmentId();
        }

        return ApprovalWaitDTO.builder()
                .approvaId(entity.getApprovaId())
                // 2. 날짜 null 체크 (DB에 날짜가 없을 경우 대비)
                .approvaDate(entity.getApprovaDate() != null ? entity.getApprovaDate().toString() : "")
                .approvaTitle(entity.getApprovaTitle())
                // 3. 작성자 null 체크 (중요: 여기서 500 에러 자주 발생)
                .writer(entity.getUserWriter() != null ? entity.getUserWriter().getUserName() : "미상")
                // 4. 문서 양식(Line) null 체크 (중요: 여기서 500 에러 자주 발생)
                .documentName(entity.getLine() != null ? entity.getLine().getDocumentName() : "기본문서")
                .approvaState(entity.getApprovaStatus())
                .departmentName(entity.getDepartmentName())
                .departmentId(deptId)
                .memo("")
                .build();
    }
}