package com.moa.server.entity.inventory;

import com.moa.server.entity.inventory.dto.LogisticsInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LogisticsRepository extends JpaRepository<LogisticsEntity, Integer> {

    @Query(value = "SELECT new com.moa.server.entity.inventory.dto.LogisticsInfoDTO(" +
            "l.logisticsId , l.productId , l.storageId , l.logisticsOrderNum , l.logisticsType , l.logisticDate, l.logisticSno , l.logisticsPrice," +
            "p.productCord, p.productName , p.productPrice , " +
            "s.storageCord , s.storageName )" +
            "FROM LogisticsEntity l " +
            "LEFT JOIN l.storage s "+
            "LEFT JOIN l.product p "+
            "WHERE l.productId = :info " +
            "ORDER BY l.logisticDate ASC "
            , countQuery = "SELECT count(l) FROM LogisticsEntity l WHERE l.productId = :info")
    Page<LogisticsInfoDTO> findInventoryDtoPage(Integer info, Pageable pageable);

}

