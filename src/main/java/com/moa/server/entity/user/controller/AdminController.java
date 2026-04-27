package com.moa.server.entity.user.controller;

import com.moa.server.entity.approval.dto.ApprovaUserDTO;
import com.moa.server.entity.user.UserEntity;
import com.moa.server.entity.user.dto.*;
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
@RequestMapping("/api")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    //권한 조회
    @GetMapping("/admin/levels")
    public ResponseEntity<?> searchAdminUsers( @PageableDefault(page = 0, size = 10, sort = "UserId", direction = Sort.Direction.ASC) Pageable pageable,  @RequestParam(defaultValue = "") String search) {
        Page<AdminUserDTO> result = adminService.getRoleList(search, pageable);
        return ResponseEntity.ok(result);
    }

    // 권한 업데이트
    @PatchMapping("/admin/levels/{userId}")
    public ResponseEntity<?> updateUserRole ( @PathVariable("userId") Integer userId, @RequestParam("roleId") Integer roleId ) {
        int result = adminService.updateUserRole(userId , roleId);
        return ResponseEntity.ok(result);
    }

    // 팀원 조회 teamMembers
    @GetMapping("/gw/teamMembers")
    public ResponseEntity<?> getTeamMembers( Integer departmentId ,@PageableDefault(page = 0, size = 10, sort = "UserId", direction = Sort.Direction.ASC) Pageable pageable,  @RequestParam(defaultValue = "") String search) {
        Page<TeamUserDTO> result = adminService.findByDepartmentIdAndUserNameContaining(departmentId, search, pageable);
        return ResponseEntity.ok(result);
    }

    // 팀원 상세 조회 teamMembers
    @GetMapping("/gw/teamMembers/{userId}")
    public ResponseEntity<?> getTeamMemberInfo ( @PathVariable("userId") Integer userId ) {
        TeamUserDTO result = adminService.findTeamByUserId( userId );
        return ResponseEntity.ok(result);
    }

    // 팀원 인사평가
    @PatchMapping("/gw/teamMembers/{userId}")
    public ResponseEntity<?> updateUserRole ( @PathVariable("userId") Integer userId, @RequestBody RequestPerformace dto ) {
        int result = adminService.updatePerformance(userId , dto.getPerformance() );
        return ResponseEntity.ok(result);
    }

    // 권한 선택
    @GetMapping("/select/role")
    public Page<?> getRoleList ( @PageableDefault(page = 0, size = 10 )Pageable pageable) {
        return adminService.getRoleCord( pageable);
    }

}
