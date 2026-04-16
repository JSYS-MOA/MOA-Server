package com.moa.server.entity.user;

import com.moa.server.entity.user.dto.AdminUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends  JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmployeeIdAndPassword(String employeeId, String password);


    UserEntity getUserByEmployeeId(String employeeId);

    // 권한 조회
    @Query(value = "SELECT new com.moa.server.entity.user.dto.AdminUserDTO(" +
            "u.roleId, u.userId, u.userName, u.employeeId, u.phone, u.email," +
            "r.name, r.code , g.gradeId , g.gradeCord , g.gradeName, " +
            "d. departmentId , d.departmentCord , d.departmentName) " +
            "FROM UserEntity u " +
            "LEFT JOIN u.role r "+
            "LEFT JOIN u.department d "+
            "LEFT JOIN u.grade g "+
            "WHERE u.userName LIKE %:search% " + // UserEntity의 'private AdminRoleEntity role' 필드로 조인
            "ORDER BY u.userId ASC "
            , countQuery = "SELECT count(u) FROM UserEntity u WHERE u.userName LIKE %:search%")
    Page<AdminUserDTO> findAdminUserList(String search , Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u SET u.roleId = :roleId WHERE u.userId = :userId")
    int updateUserIdRoleId(Integer userId, Integer roleId);


//    @Query("SELECT new com.moa.server.entity.user.dto.AdminUserDTO(" +
//            "u.roleId, u.userId, u.userName, u.employeeId, u.password, u.phone, u.email, r.name, r.code) " +
//            "FROM UserEntity u " +
//            "LEFT JOIN u.role r " + // UserEntity의 'private AdminRoleEntity role' 필드로 조인
//            "WHERE (:email IS NULL OR u.email LIKE %:email%) " +
//            "AND (:phone IS NULL OR u.phone LIKE %:phone%)")
//    List<AdminUserDTO> findAdminUserList(@Param("email") String email, @Param("phone") String phone);


}

