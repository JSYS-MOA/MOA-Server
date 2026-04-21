package com.moa.server.entity.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {

    //예시
    //List<BoardVOEntity> findByTitleContaining  (String title);
    Optional<DepartmentEntity> findByDepartmentId(Integer departmentId);
    
}

