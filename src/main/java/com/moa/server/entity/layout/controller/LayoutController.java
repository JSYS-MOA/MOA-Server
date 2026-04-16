package com.moa.server.entity.layout.controller;

import com.moa.server.entity.layout.dto.LayoutDTO;
import com.moa.server.entity.layout.service.LayoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/layout")
public class LayoutController {

    private final LayoutService layoutService;

    @GetMapping
    public LayoutDTO getLayout(@RequestParam String employeeId) {
        return layoutService.getLayout(employeeId);
    }

}
