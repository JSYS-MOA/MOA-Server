package com.moa.server.entity.layout.controller;

import com.moa.server.entity.layout.dto.LayoutDTO;
import com.moa.server.entity.layout.service.LayoutService;
import com.moa.server.entity.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/layout")
public class LayoutController {

    private final LayoutService layoutService;

    @GetMapping
    public LayoutDTO getLayout(@SessionAttribute(name = "user") UserEntity user) {
        return layoutService.getLayout(user.getEmployeeId());
    }

}
