package com.moa.server.entity.inventory;

import com.moa.server.entity.inventory.dto.ProductCordMapDTO;
import com.moa.server.entity.user.AdminRoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    // 품목 조회
    Page<ProductEntity> findAll(Pageable pageable);

}

