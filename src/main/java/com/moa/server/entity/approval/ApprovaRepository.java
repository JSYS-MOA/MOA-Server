package com.moa.server.entity.approval;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovaRepository extends JpaRepository<ApprovaEntity, Integer> {

    //내가 신청한 결재목록
    @EntityGraph(attributePaths = {"Line", "UserWriter", "UserWriter.department", "UserWriter.grade", "UserApprover" , "UserApprover.department", "UserApprover.grade"})
    Page<ApprovaEntity> findByWriter(Integer writer, Pageable pageable );

    @Query("SELECT a FROM ApprovalEntity a " +
            "JOIN FETCH a.user u " +
            "LEFT JOIN FETCH u.department d " +
            "WHERE (:startDate IS NULL OR a.approvaDate >= :startDate) " +
            "AND (:finishDate IS NULL OR a.approvaDate <= :finishDate) " +
            "AND (:category IS NULL OR d.departmentName = :category) " +
            "AND (:keyword IS NULL OR u.userName LIKE %:keyword%)")
    Page<ApprovaEntity> findApprovalList(
            @Param("startDate") String startDate,
            @Param("finishDate") String finishDate,
            @Param("category") String category,
            @Param("keyword") String keyword,
            Pageable pageable);
}

