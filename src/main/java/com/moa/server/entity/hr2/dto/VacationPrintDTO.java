package com.moa.server.entity.hr2.dto;

import com.moa.server.entity.vacation.VacationEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacationPrintDTO {
    private String departmentName;
    private String employeeId;
    private String userName;
    private Double basicVacation;
    private Double useVacation;
    private Double remainingVacation;
    private Integer vacationId;

    public static VacationPrintDTO fromEntity(VacationEntity entity) {
        return VacationPrintDTO.builder()
                .departmentName(entity.getUser().getDepartment() != null ?
                        entity.getUser().getDepartment().getDepartmentName() : "소속없음")
                .employeeId(entity.getUser().getEmployeeId())
                .userName(entity.getUser().getUserName())
                .basicVacation(entity.getBasicVacation() != null ?
                        entity.getBasicVacation().getBasicVacationDay().doubleValue() : 0.0)
                .useVacation(entity.getUseVacation()!= null ?
                        entity.getUseVacation().doubleValue() : 0.0)
                .remainingVacation(entity.getRemainingVacation()!= null ?
                        entity.getRemainingVacation().doubleValue() : 0.0)
                .vacationId(entity.getVacationId())
                .build();
    }

}
