package com.moa.server.entity.user.controller;
import com.moa.server.entity.user.UserEntity;
import com.moa.server.entity.user.dto.AdminUserDTO;
import com.moa.server.entity.user.dto.LoginRequestDTO;
import com.moa.server.entity.user.dto.LoginResponseDTO;
import com.moa.server.entity.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController // json을 반환하기 위함  @Controller + @ResponseBody
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController{

    private final UserService userService;

    //로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request, HttpSession session){
        return ResponseEntity.ok(userService.login(request,session));
    }


    //로그아웃
    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session){
        userService.logout(session);
        return ResponseEntity.ok().build();
    }

    //로그인 확인 -> 왜 하냐 -> 새로고침하면 zustand가 날아감
    //그래서 세션에서 확인하고 다시 유저 정보를 반환해서 zustand 다시 채움
    @GetMapping("/check")
    public ResponseEntity<LoginResponseDTO> check(HttpSession session){

        return ResponseEntity.ok(userService.check(session));
    }
}
