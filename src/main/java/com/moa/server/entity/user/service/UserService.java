package com.moa.server.entity.user.service;

import com.moa.server.entity.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)  //DB 작업에서 오류나면 처음으로 되돌리는(롤백) 코드 , 로그인에서는 필요없는데 Service에는 보통 붙음
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity login(String employeeId, String password){
        UserEntity user =  userRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new RuntimeException("사원코드 또는 비밀번호를 확인하세요"));

        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new RuntimeException("사원코드 또는 비밀번호를 확인하세요");
        }
        return user;
    }
}