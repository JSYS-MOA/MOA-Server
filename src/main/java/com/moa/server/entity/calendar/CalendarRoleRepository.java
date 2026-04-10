package com.moa.server.entity.calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarRoleRepository extends JpaRepository<CalendarRoleEntity, Integer> {

    //예시
    //List<BoardVOEntity> findByTitleContaining  (String title);

}

