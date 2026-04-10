package com.moa.server.entity.inventory;

import com.moa.server.entity.user.AdminRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefectRepository extends JpaRepository<DefectEntity, Integer> {

    //예시
    //List<BoardVOEntity> findByTitleContaining  (String title);

}

