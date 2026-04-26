package com.moa.server.entity.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<GradeEntity, Integer> {
    
    Optional<GradeEntity> findByGradeCord(String gradeCord);

    Optional<GradeEntity> findByGradeId(Integer gradeId);


}

