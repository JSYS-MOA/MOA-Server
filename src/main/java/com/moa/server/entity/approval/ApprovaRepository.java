package com.moa.server.entity.approval;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovaRepository extends JpaRepository<ApprovaEntity, Integer> {

    //내가 신청한 결재목록
    @EntityGraph(attributePaths = {"Line", "UserWriter", "UserWriter.department", "UserWriter.grade", "UserApprover" , "UserApprover.department", "UserApprover.grade"})
    Page<ApprovaEntity> findByWriter(Integer writer, Pageable pageable );

    // 결재 상세정보 / 팀장용 결재 상세 조회
    @EntityGraph(attributePaths = {"Line", "UserWriter", "UserWriter.department", "UserWriter.grade", "UserApprover" , "UserApprover.department", "UserApprover.grade"})
    Page<ApprovaEntity> findByApprovaId( Integer approvaId ,Pageable pageable );

    //팀장용 결재목록 조회
    @EntityGraph(attributePaths = {"Line", "UserWriter", "UserWriter.department", "UserWriter.grade", "UserApprover" , "UserApprover.department", "UserApprover.grade"})
    Page<ApprovaEntity> findByApprover(Integer approver, Pageable pageable );


}

