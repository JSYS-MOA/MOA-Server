package com.moa.server.entity.vacation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<WorkEntity, Integer> {

    //예시
    //List<BoardVOEntity> findByTitleContaining  (String title);

    @Query("SELECT w FROM WorkEntity w " +
            "JOIN FETCH w.user " +           // 유저 정보 한방에
            "LEFT JOIN FETCH w.allowance" +  // 수당 정보 한방에 (없을 수 있으니 LEFT)
            " WHERE (:keyword IS NULL OR w.user.name LIKE %:keyword%) " +
            "AND (:category IS NULL OR w.allowance.category = :category) " +
            "AND (:startDate IS NULL OR w.workDate >= :startDate) " +
            "AND (:finishDate IS NULL OR w.workDate <= :finishDate)")
    Page<WorkEntity> findAllWithDetails(
            @Param("startDate") String startDate,
            @Param("finishDate") String finishDate,
            @Param("category") String category,
            @Param("keyword") String keyword,
            Pageable pageable);
}

