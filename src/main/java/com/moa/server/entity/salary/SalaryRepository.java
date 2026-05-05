package com.moa.server.entity.salary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface SalaryRepository extends JpaRepository<SalaryEntity, Integer> {

    Optional<SalaryEntity> findByUserId(Integer userId);

    List<SalaryEntity> findByUserIdIn(Collection<Integer> userIds);
}