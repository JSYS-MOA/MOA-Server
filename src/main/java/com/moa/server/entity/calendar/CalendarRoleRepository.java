package com.moa.server.entity.calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRoleRepository extends JpaRepository<CalendarRoleEntity, Integer> {

    List<CalendarRoleEntity> findByUserId(Integer userId);

    void deleteByCalendarId(Integer calendarId);
}

