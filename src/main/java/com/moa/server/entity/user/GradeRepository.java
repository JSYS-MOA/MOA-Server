package com.moa.server.entity.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<GradeEntity, Integer> {

    //예시
    //List<BoardVOEntity> findByTitleContaining  (String title);
    
}

