package com.moa.server.entity.calendar.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CalendarRequestDTO {
    private String type;
    private Integer calendarCategoryId;
    private String eventStartDate;
    private String eventEndDate;
    private String eventTitle;
    private String eventContent;
    private Integer alarm;
    private List<Integer> sharedUserIds;
}
