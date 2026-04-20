package com.moa.server.entity.user.controller;

import com.moa.server.entity.user.UserEntity;
import com.moa.server.entity.user.dto.LoginRequestDTO;
import com.moa.server.entity.user.dto.LoginResponseDTO;
import com.moa.server.entity.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request, HttpSession session) {
        try {
            UserEntity user = userService.login(request.getEmployeeId(), request.getPassword());

            session.setAttribute("user", user);

            LoginResponseDTO response = LoginResponseDTO.builder()
                    .result(true)
                    .userName(user.getUserName())
                    .employeeId(user.getEmployeeId())
                    .departmentId(user.getDepartmentId())
                    .roleId(user.getRoleId())
                    .build();
            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            LoginResponseDTO response = LoginResponseDTO.builder()
                    .result(false)
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<LoginResponseDTO> logout(HttpSession session) {
        session.invalidate();
        LoginResponseDTO response = LoginResponseDTO.builder()
                .result(true)
                .message("로그아웃 되었습니다.")
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/check")
    public ResponseEntity<LoginResponseDTO> check(HttpSession session) {
        UserEntity loginUser = (UserEntity) session.getAttribute("user");

        if (loginUser != null) {
            LoginResponseDTO response = LoginResponseDTO.builder()
                    .result(true)
                    .employeeId(loginUser.getEmployeeId())
                    .departmentId(loginUser.getDepartmentId())
                    .roleId(loginUser.getRoleId())
                    .build();
            return ResponseEntity.ok(response);
        }

        LoginResponseDTO response = LoginResponseDTO.builder()
                .result(false)
                .message("로그인이 필요합니다.")
                .build();
        return ResponseEntity.status(401).body(response);
    }
}
