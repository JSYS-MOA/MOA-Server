package com.moa.server.entity.user.controller;

import com.moa.server.entity.user.UserEntity;
import com.moa.server.entity.user.dto.AdminUserDTO;
import com.moa.server.entity.user.dto.LoginRequestDTO;
import com.moa.server.entity.user.dto.LoginResponseDTO;
import com.moa.server.entity.user.service.AdminService;
import com.moa.server.entity.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // json을 반환하기 위함  @Controller + @ResponseBody
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/levels")
    public ResponseEntity<?> searchAdminUsers(
            @PageableDefault(page = 0, size = 10, sort = "UserId", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(defaultValue = "") String search) {

        Page<AdminUserDTO> result = adminService.getRoleList(search, pageable);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/levels/{userId}")
    public ResponseEntity<?> updateUserRole (
         @PathVariable("userId") Integer userId,
         @RequestParam("roleId") Integer roleId ) {
        int result =adminService.updateUserRole(userId , roleId);
        return ResponseEntity.ok(result);
    }


}
