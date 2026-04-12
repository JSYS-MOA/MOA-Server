package com.moa.server.entity.user.service;

import com.moa.server.entity.user.*;
import lombok.RequiredArgsConstructor;
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

    //인사 카드 전체 조회
    public List<UserEntity> getAllUsers() {return userRepository.findAll();}

}
