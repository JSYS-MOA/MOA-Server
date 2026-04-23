package com.moa.server.entity.user.service;

import com.moa.server.common.exception.CustomException;
import com.moa.server.common.exception.ErrorCode;
import com.moa.server.entity.user.*;
import com.moa.server.entity.user.dto.LoginRequestDTO;
import com.moa.server.entity.user.dto.LoginResponseDTO;
import com.moa.server.entity.user.dto.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional //DB 작업에서 오류나면 처음으로 되돌리는(롤백) 코드 , 로그인에서는 필요없는데 Service에는 보통 붙음
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //로그인
    public LoginResponseDTO login(LoginRequestDTO request, HttpSession session) {

        UserEntity user = userRepository.findByEmployeeId(request.getEmployeeId())
                .orElseThrow(() -> new CustomException(ErrorCode.LOGIN_FAILED));

        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new CustomException(ErrorCode.LOGIN_FAILED);
        }

        session.setAttribute(SessionUser.USER, new SessionUser(
                user.getEmployeeId(),
                user.getDepartmentId(),
                user.getRoleId(),
                user.getUserName(),
                user.getUserId()
        ));

        return LoginResponseDTO.builder()
                .employeeId(user.getEmployeeId())
                .departmentId(user.getDepartmentId())
                .roleId(user.getRoleId())
                .userName(user.getUserName())
                .userId(user.getUserId())
                .build();
    }

    //로그아웃
    public void logout(HttpSession session){
        session.invalidate();
    }


    //로그인 확인
    public LoginResponseDTO check(HttpSession session){
        SessionUser loginUser = (SessionUser) session.getAttribute(SessionUser.USER);
        if(loginUser == null){
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }
        return LoginResponseDTO.builder()
                .employeeId(loginUser.getEmployeeId())
                .departmentId(loginUser.getDepartmentId())
                .roleId(loginUser.getRoleId())
                .userName(loginUser.getUserName())
                .userId(loginUser.getUserId())
                .build();
    }
}