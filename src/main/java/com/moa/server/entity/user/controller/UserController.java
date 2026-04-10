package com.moa.server.entity.user.controller;

import com.moa.server.entity.user.UserEntity;
import com.moa.server.entity.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserEntity request) {

        boolean isSuccess = userService.login(request.getEmployeeId(), request.getPassword());

            Map<String, Object> response = new HashMap<>();

            if (isSuccess) {
            // entity에 없는 데이터 보내는 방법
            response.put("result", true);

            return ResponseEntity.ok(response);
        } else {
            response.put("result", false);
            response.put("message", "로그인 실패: 사번 또는 비밀번호를 확인하세요.");

            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/user/{employeeId}")
    public ResponseEntity<?> getUserInfo(@PathVariable String employeeId) {
        // userService에서 사번으로 사용자 정보를 조회
        UserEntity user = userService.loginInfo(employeeId);

        Map<String, Object> response = new HashMap<>();

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            response.put("message", "로그인 실패: 사번 또는 비밀번호를 확인하세요.");
            return ResponseEntity.ok(response);
        }
    }

}
