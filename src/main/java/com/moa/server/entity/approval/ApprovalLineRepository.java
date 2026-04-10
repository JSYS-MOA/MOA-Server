package com.moa.server.entity.approval;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalLineRepository extends JpaRepository<ApprovalLineEntity, Integer> {

    //예시
    //List<BoardVOEntity> findByTitleContaining  (String title);

}

