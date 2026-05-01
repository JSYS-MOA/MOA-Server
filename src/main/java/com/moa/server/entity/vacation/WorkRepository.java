package com.moa.server.entity.vacation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<WorkEntity, Integer> {

    @Query("SELECT w FROM WorkEntity w JOIN FETCH w.user LEFT JOIN FETCH w.allowance")
    List<WorkEntity> findAllWithDetails();

    @Query("SELECT w FROM WorkEntity w JOIN FETCH w.user LEFT JOIN FETCH w.allowance WHERE w.userId IN :userIds")
    List<WorkEntity> findByUserIdIn(@Param("userIds") Collection<Integer> userIds);
}