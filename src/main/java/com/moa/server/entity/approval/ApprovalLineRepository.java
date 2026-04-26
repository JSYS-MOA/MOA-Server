package com.moa.server.entity.approval;

import com.moa.server.entity.inventory.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalLineRepository extends JpaRepository<ApprovalLineEntity, Integer> {

    Page<ApprovalLineEntity> findAll(Pageable pageable);

}

