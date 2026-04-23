package com.moa.server.entity.hr2.dto;

import com.moa.server.entity.salary.AllowanceEntity;
import com.moa.server.entity.vacation.WorkEntity;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkDTO {
    private Integer workId;
    private String employeeId;
    private String userName;
    private LocalDate workDate;
    private String workMemo;
    private String allowanceName;

    public WorkDTO(WorkEntity entity) {
        this.workId = entity.getWorkId();
        this.employeeId = (entity.getUser() != null) ? entity.getUser().getEmployeeId() : "알수없는사번";
        this.userName = (entity.getUser() != null) ? entity.getUser().getUserName() : "알수없음";
        this.workDate = entity.getWorkDate();
        this.workMemo = entity.getWorkMemo();
        this.allowanceName = (entity.getAllowance() != null)
                ? entity.getAllowance().getAllowanceName() : null;
    }

    public void getAllowance(AllowanceEntity allowance) {
        this.allowanceName = allowance.getAllowanceName();
    }
}
