package com.moa.server.entity.calendar;

import com.moa.server.entity.user.AdminRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarRepository extends JpaRepository<CalendarEntity, Integer> {

    //예시
    //List<BoardVOEntity> findByTitleContaining  (String title);


}

