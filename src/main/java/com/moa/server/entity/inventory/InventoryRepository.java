package com.moa.server.entity.inventory;

import com.moa.server.entity.inventory.dto.InventoryDTO;
import com.moa.server.entity.inventory.dto.InventoryInfoDTO;
import com.moa.server.entity.user.AdminRoleEntity;
import com.moa.server.entity.user.dto.AdminUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Integer> {

    // 재고조회
    @Query(value = "SELECT new com.moa.server.entity.inventory.dto.InventoryDTO(" +
            "i.inventoryId, i.productId, i.storageId, i.inventorySno, i.expirationDate, i.inventoryMemo, i.logisticsId," +
            "p.productCord, p.productName , p.productPrice , " +
            "s.storageCord , s.storageName ) " +
            "FROM InventoryEntity i " +
            "LEFT JOIN i.storage s "+
            "LEFT JOIN i.product p "+
            "LEFT JOIN i.logistics l "+
            "WHERE p.productName LIKE %:search% " +
            "ORDER BY i.inventoryId ASC "
            , countQuery = "SELECT count(u) FROM UserEntity u WHERE u.userName LIKE %:search%")
    Page<InventoryDTO> findInventoryBySearch(@Param("search") String search, Pageable pageable);


//    @Query(value = "SELECT new com.moa.server.entity.inventory.dto.InventoryInfoDTO(" +
//            "i.inventoryId, i.productId, i.storageId, i.inventorySno, i.expirationDate, i.inventoryMemo, i.logisticsId," +
//            "p.productCord, p.productName , p.productPrice , " +
//            "s.storageCord , s.storageName , " +
//            "l.logisticsOrderNum , l.logisticsType , l.logisticDate, l.logisticSno , l.logisticsPrice) " +
//            "FROM InventoryEntity i " +
//            "LEFT JOIN i.storage s "+
//            "LEFT JOIN i.product p "+
//            "LEFT JOIN i.logistics l "+
//            "WHERE i.inventoryId = :info " +
//            "ORDER BY l.logisticDate ASC "
//    , countQuery = "SELECT count(i) FROM InventoryEntity i WHERE i.inventoryId = :info")
//    Page<InventoryInfoDTO> findInventoryDtoPage(@Param("info") Integer info, Pageable pageable);

}

