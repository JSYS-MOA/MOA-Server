package com.moa.server.entity.inventory;

import com.moa.server.entity.inventory.dto.InventoryInfoDTO;
import com.moa.server.entity.inventory.dto.LogisticsInfoDTO;
import com.moa.server.entity.user.AdminRoleEntity;
import jakarta.persistence.Column;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface LogisticsRepository extends JpaRepository<LogisticsEntity, Integer> {

//    @Query(value = "SELECT new com.moa.server.entity.inventory.dto.LogisticsInfoDTO(" +
//            "l.logisticsId , l.productId , l.storageId , l.logisticsOrderNum , l.logisticsType , l.logisticDate, l.logisticSno , l.logisticsPrice," +
//            "p.productCord, p.productName , p.productPrice , " +
//            "s.storageCord , s.storageName )" +
//            "FROM Logistics l " +
//            "LEFT JOIN l.storage s "+
//            "LEFT JOIN l.product p "+
//            "WHERE l.productId = :info " +
//            "ORDER BY l.logisticDate ASC "
//            , countQuery = "SELECT count(i) FROM InventoryEntity i WHERE i.inventoryId = :info")
//    Page<LogisticsInfoDTO> findInventoryDtoPage(@Param("info") Integer info, Pageable pageable);
}

