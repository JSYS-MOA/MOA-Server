package com.moa.server.entity.vacation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface VacationRepository extends JpaRepository<VacationEntity, Integer> {

    //예시
    //List<BoardVOEntity> findByTitleContaining  (String title);
    @Query("SELECT v FROM VacationEntity v " +
            "JOIN FETCH v.user u " +
            "LEFT JOIN FETCH u.department d " +
            "LEFT JOIN FETCH v.basicVacation bv " + // 휴가 일수 정보 조인
            "JOIN u.approva a " +
            " WHERE a.approvaStatus = '결재' "+
            "AND (:startDate IS NULL OR a.approvaDate >= :startDate) " +
            "AND (:finishDate IS NULL OR a.approvaDate <= :finishDate) " +
            "AND (:category IS NULL OR d.departmentName = :category) " +
            "AND (:keyword IS NULL OR u.userName LIKE %:keyword%)")
    Page<VacationEntity> findVacationPrint(
            @Param("startDate") LocalDateTime startDate,
            @Param("finishDate") LocalDateTime finishDate,
            @Param("category") String category,
            @Param("keyword") String keyword,
            Pageable pageable);
}

