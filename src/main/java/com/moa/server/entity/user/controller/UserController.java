package com.moa.server.entity.user.controller;

import com.moa.server.entity.user.UserEntity;
import com.moa.server.entity.user.dto.LoginRequestDTO;
import com.moa.server.entity.user.dto.LoginResponseDTO;
import com.moa.server.entity.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@RestController // json을 반환하기 위함  @Controller + @ResponseBody
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController{

    private final UserService userService;

    //로그인
    //ResponseEntity란 Http 응답(상태코드, 헤더, 데이터)을 담는 객체
    //RequestBody란 프론트 쪽에서 보내는 json 데이터를 받는 어노테이션
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request, HttpSession session){
        try{
            UserEntity user = userService.login(request.getEmployeeId(), request.getPassword());
    public ResponseEntity<?> login(@RequestBody @NonNull UserEntity request) {
        boolean isSuccess = userService.login(request.getEmployeeId(), request.getPassword());

            session.setAttribute("user", user);
        Map<String, Object> response = new HashMap<>();

            LoginResponseDTO response = LoginResponseDTO.builder()
                    .result(true)
                    .employeeId(user.getEmployeeId())
                    .departmentId(user.getDepartmentId())
                    .roleId(user.getRoleId())
                    .build();
        if (isSuccess) {
            response.put("result", true);
            return ResponseEntity.ok(response);

            }catch (RuntimeException e){
                LoginResponseDTO response = LoginResponseDTO.builder()
                        .result(false)
                        .message(e.getMessage())
                        .build();
                return ResponseEntity.ok(response);
            }
        }

        response.put("result", false);
        response.put("message", "로그인 실패: 사번 또는 비밀번호를 확인하세요.");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/user/{employeeId}")
    public ResponseEntity<?> getUserInfo(@PathVariable String employeeId) {
        UserEntity user = userService.loginInfo(employeeId);
    //로그아웃
    @GetMapping("/logout")
    public ResponseEntity<LoginResponseDTO> logout(HttpSession session){
        session.invalidate();
        LoginResponseDTO response = LoginResponseDTO.builder()
                .result(true)
                .message("로그아웃 되었습니다")
                .build();
        return ResponseEntity.ok(response);
    }

    //로그인 확인 -> 왜 하냐 -> 새로고침하면 zustand가 날아감
    //그래서 세션에서 확인하고 다시 유저 정보를 반환해서 zustand 다시 채움
    @GetMapping("/check")
    public ResponseEntity<LoginResponseDTO> check(HttpSession session){
        UserEntity loginUser = (UserEntity) session.getAttribute("user");

        if (user != null) {
            return ResponseEntity.ok(user);
        }

        response.put("message", "로그인 실패: 사번 또는 비밀번호를 확인하세요.");
        return ResponseEntity.ok(response);
    }
}

        if(loginUser != null){
            LoginResponseDTO response = LoginResponseDTO.builder()
                    .result(true)
                    .employeeId(loginUser.getEmployeeId())
                    .departmentId(loginUser.getDepartmentId())
                    .roleId(loginUser.getRoleId())
                    .build();
            return ResponseEntity.ok(response);
        }else{
            LoginResponseDTO response = LoginResponseDTO.builder()
                    .result(false)
                    .message("로그인이 필요합니다")
                    .build();
            return ResponseEntity.status(401).body(response);
        }
    }
}
