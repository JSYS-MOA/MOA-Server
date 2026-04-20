package com.moa.server.entity.inventory.controller;


import com.moa.server.entity.inventory.dto.DefectDTO;
import com.moa.server.entity.inventory.service.InventoryService;
import com.moa.server.entity.user.dto.AdminUserDTO;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Page<DefectDTO> getDefectListBySearch(
            @Param("search") String search,
            @PageableDefault(page = 0, size = 10 )Pageable pageable) {
        return inventoryService.getDefectList( search, pageable );
    }

}
