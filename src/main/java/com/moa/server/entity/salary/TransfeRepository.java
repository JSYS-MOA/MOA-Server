package com.moa.server.entity.salary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransfeRepository extends JpaRepository<TransfeEntity, Integer> {

    List<TransfeEntity> findBySalaryLedger_SalaryLedgerId(Integer salaryLedgerId);

    long deleteBySalaryLedger_SalaryLedgerId(Integer salaryLedgerId);
}
