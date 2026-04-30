package com.moa.server.entity.calendar.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CalendarResponseDTO {

    private Integer calendarId;
    private Integer writer;
    private String type;
    private Integer calendarCategoryId;
    private String calendarCategoryName;
    private String eventStartDate;
    private String eventEndDate;
    private String eventTitle;
    private String eventContent;
    private String file;
    private Integer alarm;
    private String writerName;
    private List<Integer> sharedUserIds;
}

