package com.moa.server.entity.inventory.controller;


import com.moa.server.entity.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 결재관련

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/status")
    public Page<?> findInventoryBySearch(
            @Param("search") String search,
            @PageableDefault(page = 0, size = 10 )Pageable pageable) {
        return inventoryService.getInventoryList(search, pageable);
    }

    @GetMapping("/status/{info}")
    public Page<?> findInventoryDtoBySearch(
            @PathVariable("info") Integer info,
            @PageableDefault(page = 0, size = 10 )Pageable pageable) {
        return inventoryService.getInventoryListInfo(info, pageable);
    }

    @GetMapping("/disposals")
    public Page<?> getDefectListBySearch(
            @Param("search") String search,
            @PageableDefault(page = 0, size = 10 )Pageable pageable) {
        return inventoryService.getDefectList( search, pageable );
    }

    @GetMapping("/disposals/{info}")
    public Page<?> findDefectDtoBySearch(
            @PathVariable("info") Integer info,
            @PageableDefault(page = 0, size = 10 )Pageable pageable) {
        return inventoryService.getDefectListInfo(info, pageable);
    }

    @GetMapping("/orders")
    public Page<?> getOrdersListBySearch(
            @Param("search") String search,
            @PageableDefault(page = 0, size = 10 )Pageable pageable) {
        return inventoryService.getOrdererList( search, pageable );
    }

    @GetMapping("/orders/{info}")
    public Page<?> findOrdersDtoBySearch(
            @PathVariable("info") Integer info,
            @PageableDefault(page = 0, size = 10 )Pageable pageable) {
        return inventoryService.getOrdererListInfo(info, pageable);
    }

}
