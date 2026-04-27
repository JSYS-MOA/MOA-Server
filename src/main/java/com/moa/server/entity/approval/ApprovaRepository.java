package com.moa.server.entity.approval;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ApprovaRepository extends JpaRepository<ApprovaEntity, Integer> {

    //내가 신청한 결재목록
    @EntityGraph(attributePaths = {"line", "userWriter", "userWriter.department", "userWriter.grade", "lineApprover" , "lineApprover.userApprover" , "lineApprover.userApprover.department", "lineApprover.userApprover.grade"})
    Page<ApprovaEntity> findByWriter(Integer writer, Pageable pageable );

    // 결재 상세정보 / 팀장용 결재 상세 조회
    @EntityGraph(attributePaths = {"line", "userWriter", "userWriter.department", "userWriter.grade", "lineApprover" , "lineApprover.userApprover" , "lineApprover.userApprover.department", "lineApprover.userApprover.grade"})
    Page<ApprovaEntity> findByApprovaId( Integer approvaId ,Pageable pageable );

    //팀장용 결재목록 조회
    @EntityGraph(attributePaths = {"line", "userWriter", "userWriter.department", "userWriter.grade", "lineApprover" , "lineApprover.userApprover" , "lineApprover.userApprover.department", "lineApprover.userApprover.grade"})
    Page<ApprovaEntity> findByLineApprover_ApprovalLineUser(Integer approvalLineUser, Pageable pageable );

    //  미결재 결재 삭제
    void deleteByApprovaId(Integer approvaId);

    // 팀장 결제 내역 반려 / 결재 처리 approvalAct/{approva_id}
    @Modifying
    @Transactional
    @Query("UPDATE ApprovaEntity a SET a.approvaStatus = :approvaStatus WHERE a.approvaId = :approvaId")
    int updateApprovaIdApprovaStatus(Integer  approvaId , String approvaStatus);


}

