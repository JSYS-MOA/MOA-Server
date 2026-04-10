package com.moa.server.entity.notice;

import com.moa.server.entity.user.AdminRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Integer> {

    //예시
    //List<BoardVOEntity> findByTitleContaining  (String title);

}

