package com.moa.server.entity.calendar.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CalendarCategoryResponseDTO {
    private Integer calendarCategoryId;
    private String calendarCategoryName;
}