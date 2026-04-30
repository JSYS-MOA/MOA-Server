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

    public static ApprovalWaitDTO fromEntity(ApprovaEntity entity) {

        String saveDeptName = "예외부서"; // 기본값 설정

        // 1. 부서 매칭 로직 (LineName에 DeptName이 포함되는지 확인)
        if (entity.getLine() != null && entity.getUserWriter() != null && entity.getUserWriter().getDepartment() != null) {
            String lineName = entity.getLineApprover().getApprovalLineName();
            String deptName = entity.getUserWriter().getDepartment().getDepartmentName();

            if (lineName != null && deptName != null && lineName.contains(deptName)) {
                saveDeptName = deptName; // 포함되면 깔끔한 부서명 ("인사팀")
            } else {
                saveDeptName = (lineName != null) ? lineName : "부서미지정";
            }
        }

        return ApprovalWaitDTO.builder()
                .approvaId(entity.getApprovaId())
                .approvaDate(entity.getApprovaDate().toString())
                .approvaTitle(entity.getApprovaTitle())
                .writer(entity.getUserWriter().getUserName()) // User 조인 결과
                .documentName(entity.getLine().getDocumentName())
                .approvaState(entity.getApprovaStatus())
                .departmentName(saveDeptName)
                .memo("")
                .build();
    }
}
