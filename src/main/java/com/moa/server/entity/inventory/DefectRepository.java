package com.moa.server.entity.inventory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefectRepository extends JpaRepository<DefectEntity, Integer> {

    @EntityGraph(attributePaths = {"user", "inventory" , "inventory.product"})
    Page<DefectEntity> findByInventory_Product_ProductNameContaining(String productName, Pageable pageable);

    @EntityGraph(attributePaths = {"user", "inventory" , "inventory.product" , "inventory.storage"})
    Page<DefectEntity> findByInventoryId(Integer productId, Pageable pageable);

}

