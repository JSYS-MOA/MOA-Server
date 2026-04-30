package com.moa.server.entity.inventory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {

    @Override
    @EntityGraph(attributePaths = {"vendorId", "salaryLedgerId"})
    List<TransactionEntity> findAll();

    @Override
    @EntityGraph(attributePaths = {"vendorId", "salaryLedgerId"})
    Page<TransactionEntity> findAll(Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"vendorId", "salaryLedgerId"})
    Optional<TransactionEntity> findById(Integer transactionId);




    @EntityGraph(attributePaths = {"vendorId", "salaryLedgerId"})
    List<TransactionEntity> findBySalaryLedgerId(Integer salaryLedgerId);

    @EntityGraph(attributePaths = {"vendorId", "salaryLedgerId"})
    Optional<TransactionEntity> findFirstBySalaryLedgerId(Integer salaryLedgerId);

    @EntityGraph(attributePaths = {"vendorId", "salaryLedgerId"})
    List<TransactionEntity> findByVendorId(Integer vendorId);

    @EntityGraph(attributePaths = {"vendorId", "salaryLedgerId"})
    Optional<TransactionEntity> findFirstByVendorId(Integer vendorId);

    @EntityGraph(attributePaths = {"vendorId", "salaryLedgerId"})
    List<TransactionEntity> findByCreatedAt(LocalDateTime createdAt);

    @EntityGraph(attributePaths = {"vendorId", "salaryLedgerId"})
    List<TransactionEntity> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    @EntityGraph(attributePaths = {"vendorId", "salaryLedgerId"})
    List<TransactionEntity> findByUpdatedAtBetween(LocalDateTime start, LocalDateTime end);

    @EntityGraph(attributePaths = {"vendorId", "salaryLedgerId"})
    List<TransactionEntity> findBySalaryLedgerIdIsNull();

    @EntityGraph(attributePaths = {"vendorId", "salaryLedgerId"})
    List<TransactionEntity> findByVendorIdIsNull();

    @EntityGraph(attributePaths = {"vendorId", "salaryLedgerId"})
    List<TransactionEntity> findByCreatedAtIsNull();

    @EntityGraph(attributePaths = {"vendorId", "salaryLedgerId"})
    Page<TransactionEntity> findByCreatedAtIsNull(Pageable pageable);

    @EntityGraph(attributePaths = {"vendorId", "salaryLedgerId"})
    List<TransactionEntity> findByUpdatedAtIsNull();

    @EntityGraph(attributePaths = {"vendorId", "salaryLedgerId"})
    Page<TransactionEntity> findByUpdatedAtIsNull(Pageable pageable);

    @EntityGraph(attributePaths = {"vendorId", "salaryLedgerId"})
    List<TransactionEntity> findBySalaryLedgerIdIsNotNull();

    @EntityGraph(attributePaths = {"vendorId", "salaryLedgerId"})
    List<TransactionEntity> findByVendorIdIsNotNull();

    @EntityGraph(attributePaths = {"vendorId", "salaryLedgerId"})
    List<TransactionEntity> findByCreatedAtIsNotNull();

    @EntityGraph(attributePaths = {"vendorId", "salaryLedgerId"})
    Page<TransactionEntity> findByCreatedAtIsNotNull(Pageable pageable);

    @EntityGraph(attributePaths = {"vendorId", "salaryLedgerId"})
    List<TransactionEntity> findByUpdatedAtIsNotNull();

    @EntityGraph(attributePaths = {"vendorId", "salaryLedgerId"})
    Page<TransactionEntity> findByUpdatedAtIsNotNull(Pageable pageable);
}
