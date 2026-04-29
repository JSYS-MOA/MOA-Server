package com.moa.server.entity.salary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransfeRepository extends JpaRepository<SalaryLedgerEntity, Integer> {

    //예시
    //List<BoardVOEntity> findByTitleContaining  (String title);


    //모든 값 검색
    @Override
    @EntityGraph(attributePaths = {"vendor", "user"})
    List<SalaryLedgerEntity> findAll();

    //페이지 계산
    @Override
    @EntityGraph(attributePaths = {"vendor", "user"})
    Page<SalaryLedgerEntity> findAll(Pageable pageable);

    //순서
    @Override
    @EntityGraph(attributePaths = {"vendor", "user"})
    Optional<SalaryLedgerEntity> findById(Integer integer);


    //검색용
    @EntityGraph(attributePaths = {"vendor", "user"})
    Optional<SalaryLedgerEntity> findBySalaryLedgerUserId(Integer UserId);

    @EntityGraph(attributePaths = {"vendor", "user"})
    Optional<SalaryLedgerEntity> findBySalaryLedgerUserIdContaining(Integer UserId);

    @EntityGraph(attributePaths = {"vendor", "user"})
    Optional<SalaryLedgerEntity> findBySalaryLedgerSalaryDate(LocalDate SalaryDate);

    @EntityGraph(attributePaths = {"vendor", "user"})
    Optional<SalaryLedgerEntity> findBySalaryLedgerSalaryDateContaining(LocalDate SalaryDate);

    //값이 널인것만 찾기
    @EntityGraph(attributePaths = {"vendor", "user"})
    List<SalaryLedgerEntity> findByCreatedAtIsNull();

    @EntityGraph(attributePaths = {"vendor", "user"})
    Page<SalaryLedgerEntity> ffindByCreatedAtIsNull(Pageable pageable);

    @EntityGraph(attributePaths = {"vendor", "user"})
    List<SalaryLedgerEntity> findByUpdatedAtIsNull();

    @EntityGraph(attributePaths = {"vendor", "user"})
    Page<SalaryLedgerEntity> findByUpdatedAtIsNull(Pageable pageable);



}

