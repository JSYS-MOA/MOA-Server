package com.moa.server.entity.layout.controller;

import com.moa.server.entity.layout.dto.LayoutDTO;
import com.moa.server.entity.layout.service.LayoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class LayoutController {

    private final LayoutService layoutService;

    @GetMapping("/layout")
    public LayoutDTO getLayout(@RequestParam("employeeId") String employeeId) {
        return layoutService.getLayout(employeeId);
    }

}
