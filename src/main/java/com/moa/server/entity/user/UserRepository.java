package com.moa.server.entity.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends  JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmployeeId(String employeeId);

    boolean existsByGradeId(Integer gradeId);

    List<UserEntity> findByDepartmentId(Integer departmentId);

}

