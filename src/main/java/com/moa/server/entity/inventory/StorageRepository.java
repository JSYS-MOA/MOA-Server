package com.moa.server.entity.inventory;

import com.moa.server.entity.user.AdminRoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends JpaRepository<StorageEntity, Integer> {

    // 창고 조회
    Page<StorageEntity> findAll(Pageable pageable);

}

