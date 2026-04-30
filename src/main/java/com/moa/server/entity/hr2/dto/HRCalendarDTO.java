package com.moa.server.entity.hr2.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HRCalendarDTO {

    //Repository에서 년월일 분리
    private int year;
    private int month;
    private int day;

    //총 출근인원(내근+외근)
    private long totalCount;
    private long internalCount;
    private long externalCount;

}
