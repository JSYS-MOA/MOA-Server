package com.moa.server.entity.calendar.service;

import com.moa.server.entity.calendar.CalendarCategoryRepository;
import com.moa.server.entity.calendar.CalendarRepository;
import com.moa.server.entity.calendar.CalendarRoleRepository;
import com.moa.server.entity.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CalendarService {
    private final UserRepository userRepository;

    private final CalendarRepository calenderRepository;
    private final CalendarCategoryRepository calenderCategoryRepository;
    private final CalendarRoleRepository calenderRoleRepository;

    // 일정 생성, 카테고리 분류, 일정 공유 권한 제어
}
