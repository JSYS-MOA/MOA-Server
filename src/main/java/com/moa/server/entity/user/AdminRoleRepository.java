package com.moa.server.entity.user;

import com.moa.server.entity.approval.ApprovalLineEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRoleRepository extends JpaRepository<AdminRoleEntity, Integer> {

    Page<AdminRoleEntity> findAll(Pageable pageable);

}

