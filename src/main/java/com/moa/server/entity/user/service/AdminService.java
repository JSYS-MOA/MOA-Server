package com.moa.server.entity.user.service;

import com.moa.server.entity.inventory.InventoryEntity;
import com.moa.server.entity.inventory.dto.InventoryDTO;
import com.moa.server.entity.user.UserEntity;
import com.moa.server.entity.user.UserRepository;
import com.moa.server.entity.user.dto.AdminUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional  //DB 작업에서 오류나면 처음으로 되돌리는(롤백) 코드 , 로그인에서는 필요없는데 Service에는 보통 붙음
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    // 권한 조회
    public Page<AdminUserDTO> getRoleList(String userName, Pageable pageable) {

        Page<UserEntity> entityPage = userRepository.findWithRoleByUserNameContaining( userName,  pageable);

        // .map()을 통해 간단하게 DTO로 변환
        return entityPage.map(UserEntity::toDTO);
    }


    public int updateUserRole ( Integer userId, Integer roleId ) {
        return userRepository.updateUserIdRoleId( userId , roleId);
    }


}
