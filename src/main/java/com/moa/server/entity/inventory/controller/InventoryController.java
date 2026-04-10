package com.moa.server.entity.inventory.controller;


import com.moa.server.entity.calendar.service.CalendarService;
import com.moa.server.entity.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

// 결재관련

@RestController
// @RequestMapping("/")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService  inventoryService;

}
