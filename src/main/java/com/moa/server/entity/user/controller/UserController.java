package com.moa.server.entity.user.controller;

import com.moa.server.entity.user.UserEntity;
import com.moa.server.entity.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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

    //인사 카드 리스트
    @GetMapping("/hr/cards")
    public ResponseEntity<?> hrCardList() {
        List<UserEntity> user = userService.hrCardList();

        if (!user.isEmpty()) {
            return ResponseEntity.ok(user);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "등록된 인사카드가 없습니다.");
            return ResponseEntity.ok(response);
        }
    }

    //인사 카드 상세
    @GetMapping("/hr/cards/{user_id}")
    public ResponseEntity<?> hrCardInfo(@PathVariable Integer user_id){
        UserEntity user = userService.hrCardInfo(user_id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "해당하는 사번의 직원을 찾을 수 없습니다.");
            return ResponseEntity.ok(response);
        }

    }

    //인사 카드 수정
    @PutMapping("/hr/cards/{user_id}")
    public ResponseEntity<?> hrCardUpdate(@PathVariable Integer user_id, @RequestBody UserEntity user) {
        try {
            UserEntity users = userService.hrCardUpdate(user_id,user);

            Map<String, Object> response = new HashMap<>();

            if (users != null) {
                response.put("result", true);
                response.put("message", "인사카드 수정 완료");
                response.put("user", users);
                return ResponseEntity.ok(response);
            } else {
                response.put("result", false);
                response.put("message", "해당 사용자가 없습니다.");
                return ResponseEntity.badRequest().body(response);
            }
        } catch(Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("result", false);
            response.put("message", "인사카드 수정 실패: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    //인사 카드 추가
    @PostMapping("/hr/cards/add")
    public ResponseEntity<?> hrCardAdd(@RequestBody UserEntity user) {
        try {
            UserEntity users = userService.hrCardAdd(user);

            Map<String, Object> response = new HashMap<>();
            response.put("result", true);
            response.put("message", "인사카드 등록 완료");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("result", false);
            response.put("message", "인사카드 등록 실패: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    //인사 카드 삭제
    @DeleteMapping("/hr/cards/{user_id}")
    public ResponseEntity<?> hrCardDelete(@PathVariable Integer user_id) {
        try {
            userService.hrCardDelete(user_id);

            Map<String, Object> response = new HashMap<>();
            response.put("result", true);
            response.put("message", "인사카드 삭제 완료");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("result", false);
            response.put("message", "인사카드 삭제 실패: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
