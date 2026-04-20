package com.moa.server.entity.inventory;

import com.moa.server.entity.inventory.dto.InventoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Integer> {

    //재고조회
    @EntityGraph(attributePaths = {"product", "storage"})
    Page<InventoryEntity> findByProduct_ProductNameContaining(String productName, Pageable pageable);

}

