package com.moa.server.entity.todo;

import com.moa.server.entity.user.AdminRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {

    //예시
    //List<BoardVOEntity> findByTitleContaining  (String title);

}

