package com.moa.server.entity.calendar;

import com.moa.server.entity.user.AdminRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<CalendarEntity, Integer> {

    List<CalendarEntity> findByWriterAndType(Integer writer, String type);

    List<CalendarEntity> findByCalendarIdIn(List<Integer> calendarIds);

    List<CalendarEntity> findByWriterOrCalendarIdIn(Integer writer, List<Integer> calendarIds);
    List<CalendarEntity> findByWriter(Integer writer);
}

