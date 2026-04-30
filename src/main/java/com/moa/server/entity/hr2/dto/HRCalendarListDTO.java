package com.moa.server.entity.hr2.dto;

public class HRCalendarListDTO {
    // 1. 일자 (레파지토리에서 쪼개서 가져온 '일' 또는 전체 날짜 문자열)
    // 캘린더에서 클릭한 날짜와 매칭하기 위해 필요합니다.
    private String workDate;
    private String userName;
    private String departmentName;
    private String checkInTime;
    private String checkOutTime;

}
