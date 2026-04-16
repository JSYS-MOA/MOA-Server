package com.moa.server.entity.user.controller;

import com.moa.server.entity.user.UserEntity;
import com.moa.server.entity.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @NonNull UserEntity request) {
        boolean isSuccess = userService.login(request.getEmployeeId(), request.getPassword());

        Map<String, Object> response = new HashMap<>();

        if (isSuccess) {
            response.put("result", true);
            return ResponseEntity.ok(response);
        }

        response.put("result", false);
        response.put("message", "로그인 실패: 사번 또는 비밀번호를 확인하세요.");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/user/{employeeId}")
    public ResponseEntity<?> getUserInfo(@PathVariable String employeeId) {
        UserEntity user = userService.loginInfo(employeeId);

        Map<String, Object> response = new HashMap<>();

        if (user != null) {
            return ResponseEntity.ok(user);
        }

        response.put("message", "로그인 실패: 사번 또는 비밀번호를 확인하세요.");
        return ResponseEntity.ok(response);
    }
}
