package com.moa.server.entity.user.service;

import com.moa.server.entity.user.UserEntity;
import com.moa.server.entity.user.UserRepository;
import com.moa.server.entity.user.dto.AdminUserDTO;
import com.moa.server.entity.user.dto.TeamUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    // 권한 업데이트
    public int updateUserRole ( Integer userId, Integer roleId ) {
        return userRepository.updateUserIdRoleId( userId , roleId);
    }

    // 팀원 조회 teamMembers
    public Page<TeamUserDTO> findByDepartmentIdAndUserNameContaining(Integer departmentId , String search, Pageable pageable) {

        Page<UserEntity> entityPage = userRepository.findByDepartmentIdAndUserNameContaining( departmentId , search,  pageable);

        // .map()을 통해 간단하게 DTO로 변환
        return entityPage.map(UserEntity::teamDTO);
    }

    //  팀원 상세 조회 teamMembers/{department_id}
    public  TeamUserDTO findTeamByUserId (  Integer userId ) {
        return userRepository.findTeamByUserId( userId );
    }

    // 팀원 인사평가 
    public int updatePerformance ( Integer userId, String performance ) {
        return userRepository.updateUserIdPerformance( userId , performance);
    }
}
