package com.moa.server.entity.user;

import com.moa.server.entity.inventory.InventoryEntity;
import com.moa.server.entity.user.dto.AdminUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
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

    Optional<UserEntity> findByEmployeeId(String employeeId);


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

