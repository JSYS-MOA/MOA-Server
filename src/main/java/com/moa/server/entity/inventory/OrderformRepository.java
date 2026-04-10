package com.moa.server.entity.inventory;

import com.moa.server.entity.user.AdminRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderformRepository extends JpaRepository<OrderformEntity, Integer> {

    //예시
    //List<BoardVOEntity> findByTitleContaining  (String title);

}

