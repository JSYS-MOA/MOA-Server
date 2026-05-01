package com.moa.server.entity.hr2.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HRCountDTO {
    private LocalDate date;
    private Integer totalCount;
    private List<HRCalendarDTO> details;
}
