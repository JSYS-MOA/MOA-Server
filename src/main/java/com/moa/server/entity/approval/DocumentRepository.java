package com.moa.server.entity.approval;

import com.moa.server.entity.user.AdminRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Integer> {

    //예시
    //List<BoardVOEntity> findByTitleContaining  (String title);

    List<DocumentEntity> findByDocumentNameContaining(String documentName);

}

