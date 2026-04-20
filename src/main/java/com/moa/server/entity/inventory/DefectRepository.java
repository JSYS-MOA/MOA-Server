package com.moa.server.entity.inventory;

import com.moa.server.entity.user.AdminRoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DefectRepository extends JpaRepository<DefectEntity, Integer> {

    @EntityGraph(attributePaths = {"user", "inventory" , "inventory.product"})
    Page<DefectEntity> findByProduct_ProductNameContaining(String productName, Pageable pageable);


}

