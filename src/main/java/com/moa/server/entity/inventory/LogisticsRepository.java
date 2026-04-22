package com.moa.server.entity.inventory;

import com.moa.server.entity.inventory.dto.LogisticsInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogisticsRepository extends JpaRepository<LogisticsEntity, Integer> {

    //재고 상세 조회
    @EntityGraph(attributePaths = {"product", "storage"})
    Page<LogisticsEntity> findByProduct_ProductId(Integer productId, Pageable pageable);

    //입고 조회
    @EntityGraph(attributePaths = {"product", "storage" })
    @Query("SELECT l FROM LogisticsEntity l WHERE l.logisticsType = 'IN'")
    List<LogisticsEntity> findByLogisticsType();

    //입고 상세 조회
    @EntityGraph(attributePaths = {"product", "storage" })
    @Query("SELECT l FROM LogisticsEntity l WHERE l.logisticsType = 'IN' AND  l.logisticsOrderNum = :logisticsOrderNum")
    Page<LogisticsEntity> findByLogisticsTypeAndLogisticsOrderNum( Integer logisticsOrderNum , Pageable pageable);

    //출고 조회
    @EntityGraph(attributePaths = {"product", "storage" })
    @Query("SELECT l FROM LogisticsEntity l WHERE l.logisticsType = 'OUT'")
    List<LogisticsEntity> findByLogisticsTypeOut();

    //출고  상세 조회
    @EntityGraph(attributePaths = {"product", "storage" })
    @Query("SELECT l FROM LogisticsEntity l WHERE l.logisticsType = 'OUT' AND  l.logisticsOrderNum = :logisticsOrderNum")
    Page<LogisticsEntity> findByLogisticsTypeOutAndLogisticsOrderNum( Integer logisticsOrderNum , Pageable pageable);

}

