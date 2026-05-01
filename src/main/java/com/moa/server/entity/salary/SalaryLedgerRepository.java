package com.moa.server.entity.salary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface SalaryLedgerRepository extends JpaRepository<SalaryLedgerEntity, Integer> {

    List<SalaryLedgerEntity> findBySalaryLedgerIdIn(Collection<Integer> salaryLedgerIds);

    Optional<SalaryLedgerEntity> findByUserId(Integer userId);
}