package com.moa.server.entity.vacation;

import com.moa.server.entity.user.AdminRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkRepository extends JpaRepository<WorkEntity, Integer> {

    //예시
    //List<BoardVOEntity> findByTitleContaining  (String title);

}

