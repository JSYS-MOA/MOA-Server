package com.moa.server.entity.notice;

import com.moa.server.entity.user.AdminRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Integer> {

   List<NoticeEntity> findAllByOrderByPostDateDesc();
}

