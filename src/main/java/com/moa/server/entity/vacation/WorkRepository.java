package com.moa.server.entity.vacation;

import com.moa.server.entity.user.AdminRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<WorkEntity, Integer> {

    //예시
    //List<BoardVOEntity> findByTitleContaining  (String title);

    @Query("SELECT w FROM WorkEntity w " +
            "JOIN FETCH w.user " +           // 유저 정보 한방에
            "LEFT JOIN FETCH w.allowance")   // 수당 정보 한방에 (없을 수 있으니 LEFT)
    List<WorkEntity> findAllWithDetails();
    WorkEntity findByUserIdAndWorkDate(Integer userId, LocalDate workDate);
}

