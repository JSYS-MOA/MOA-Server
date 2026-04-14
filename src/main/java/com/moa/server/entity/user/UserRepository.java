package com.moa.server.entity.user;

import com.moa.server.entity.user.dto.AdminUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    boolean existsByEmployeeIdAndPassword(String employeeId, String password);

    UserEntity getUserByEmployeeId(String employeeId);

    @Query("SELECT new com.moa.server.entity.user.dto.AdminUserDTO(" +
            "u.roleId, u.userId, u.userName, u.employeeId, u.password, u.phone, u.email, r.name, r.code) " +
            "FROM UserEntity u " +
            "LEFT JOIN u.role r "+
            "WHERE ( u.userName LIKE %:searchParam% )")// UserEntity의 'private AdminRoleEntity role' 필드로 조인
     List<AdminUserDTO> findAdminUserList( String searchParam , Pageable pageable);



//    @Query("SELECT new com.moa.server.entity.user.dto.AdminUserDTO(" +
//            "u.roleId, u.userId, u.userName, u.employeeId, u.password, u.phone, u.email, r.name, r.code) " +
//            "FROM UserEntity u " +
//            "LEFT JOIN u.role r " + // UserEntity의 'private AdminRoleEntity role' 필드로 조인
//            "WHERE (:email IS NULL OR u.email LIKE %:email%) " +
//            "AND (:phone IS NULL OR u.phone LIKE %:phone%)")
//    List<AdminUserDTO> findAdminUserList(@Param("email") String email, @Param("phone") String phone);
}

