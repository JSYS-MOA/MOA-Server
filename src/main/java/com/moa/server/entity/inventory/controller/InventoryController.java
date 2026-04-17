package com.moa.server.entity.inventory.controller;


import com.moa.server.entity.calendar.service.CalendarService;
import com.moa.server.entity.inventory.dto.InventoryDTO;
import com.moa.server.entity.inventory.dto.InventoryInfoDTO;
import com.moa.server.entity.inventory.service.InventoryService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 결재관련

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/status")
    public Page<?> findInventoryBySearch(@Param("search") String search, Pageable pageable) {
        return inventoryService.findInventoryBySearch(search, pageable);
    }

//    @GetMapping("/status/{info}")
//    public Page<?> findInventoryDtoBySearch(@PathParam("info") Integer info, Pageable pageable) {
//        return inventoryService.findInventoryDtoBySearch(info, pageable);
//    }

}
