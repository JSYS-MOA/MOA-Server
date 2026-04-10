package com.moa.server.entity.menu;

import com.moa.server.entity.user.AdminRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritRepository extends JpaRepository<FavoritEntity, Integer> {

    //예시
    //List<BoardVOEntity> findByTitleContaining  (String title);

}

