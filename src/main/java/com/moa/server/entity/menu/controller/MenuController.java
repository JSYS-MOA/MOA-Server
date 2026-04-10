package com.moa.server.entity.menu.controller;

import com.moa.server.entity.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

// 결재관련

@RestController
// @RequestMapping("/")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;


}
