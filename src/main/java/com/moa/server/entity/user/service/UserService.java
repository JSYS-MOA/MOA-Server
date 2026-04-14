package com.moa.server.entity.user.service;

import com.moa.server.entity.user.*;
import com.moa.server.entity.user.dto.AdminUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// 유저 인사관련
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final AdminRoleRepository adminRoleRepository;
    private final DepartmentRepository departmentRepository;
    private final GradeRepository gradeRepository;


    public boolean login(String employeeId, String password) {
        return userRepository.existsByEmployeeIdAndPassword( employeeId , password );
    }

    public UserEntity loginInfo(String employeeId) {
        return userRepository.getUserByEmployeeId(employeeId);
    }


    public List<AdminUserDTO> getAdminUserList(String searchParam , Pageable pageable) {

        return userRepository.findAdminUserList( searchParam , pageable);
    }

//    public List<AdminUserDTO> getAdminUserList(String email, String phone) {
//        // Repository의 @Query 호출
//        return userRepository.findAdminUserList(email, phone);
//    }


}
