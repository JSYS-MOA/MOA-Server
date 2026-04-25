package com.moa.server.entity.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends  JpaRepository<UserEntity, Integer> {

    @Override
    @EntityGraph(attributePaths = {"department", "grade"})
    List<UserEntity> findAll();

    @Override
    @EntityGraph(attributePaths = {"department", "grade"})
    Page<UserEntity> findAll(Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"department", "grade"})
    Optional<UserEntity> findById(Integer integer);

    Optional<UserEntity> findByEmployeeIdAndPassword(String employeeId, String password);


    boolean existsByEmployeeIdAndPassword(String employeeId, String password);

    @EntityGraph(attributePaths = {"department", "grade"})
    List<UserEntity> findByUserNameContaining(String userName);

    @EntityGraph(attributePaths = {"department", "grade"})
    Page<UserEntity> findByUserNameContaining(String userName, Pageable pageable);

    @EntityGraph(attributePaths = {"department", "grade"})
    List<UserEntity> findByEmployeeIdContaining(String employeeId);

    @EntityGraph(attributePaths = {"department", "grade"})
    Page<UserEntity> findByEmployeeIdContaining(String employeeId, Pageable pageable);


    @EntityGraph(attributePaths = {"department", "grade"})
    List<UserEntity> findByQuitDateIsNotNull();

    @EntityGraph(attributePaths = {"department", "grade"})
    Page<UserEntity> findByQuitDateIsNotNull(Pageable pageable);

    @EntityGraph(attributePaths = {"department", "grade"})
    List<UserEntity> findByQuitDateIsNull();

    @EntityGraph(attributePaths = {"department", "grade"})
    Page<UserEntity> findByQuitDateIsNull(Pageable pageable);

    @EntityGraph(attributePaths = {"department", "grade"})
    List<UserEntity> findByDepartmentId(Integer departmentId);

    @EntityGraph(attributePaths = {"department", "grade"})
    Page<UserEntity> findByDepartmentId(Integer departmentId, Pageable pageable);

    @EntityGraph(attributePaths = {"department", "grade"})
    List<UserEntity> findByGradeId(Integer gradeId);

    @EntityGraph(attributePaths = {"department", "grade"})
    Page<UserEntity> findByGradeId(Integer gradeId, Pageable pageable);

    @EntityGraph(attributePaths = {"department", "grade"})
    List<UserEntity> findByBank(String bank);

    @EntityGraph(attributePaths = {"department", "grade"})
    Page<UserEntity> findByBank(String bank, Pageable pageable)
            ;
    Optional<UserEntity> findByEmployeeId(String employeeId);
    boolean existsByGradeId(Integer gradeId);


    UserEntity getUserByEmployeeId(String employeeId);

    //권한 조회
    //재고조회
    @EntityGraph(attributePaths = {"role", "department" , "grade" })
    Page<UserEntity> findByUserNameContaining(String userName, Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u SET u.roleId = :roleId WHERE u.userId = :userId")
    int updateUserIdRoleId(Integer userId, Integer roleId);


}

