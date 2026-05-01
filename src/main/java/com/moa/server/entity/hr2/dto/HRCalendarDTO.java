package com.moa.server.entity.hr2.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HRCalendarDTO {

    private LocalDateTime workDate;
    private String userName;
    private String departmentName;
    private LocalDateTime startWork;
    private LocalDateTime finishWork;


}
