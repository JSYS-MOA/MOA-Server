package com.moa.server.entity.inventory.controller;


import com.moa.server.entity.inventory.dto.LogisticsRequestDTO;
import com.moa.server.entity.inventory.dto.OrderFormPostDTO;
import com.moa.server.entity.inventory.dto.OrderPutDTO;
import com.moa.server.entity.inventory.dto.OutLogisticsRequestDTO;
import com.moa.server.entity.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 결재관련

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    // 재고 조회
    @GetMapping("/status")
    public Page<?> findInventoryBySearch(@Param("search") String search, @PageableDefault(page = 0, size = 10 )Pageable pageable) {
        return inventoryService.getInventoryList(search, pageable);
    }

    // 재고 상세 조회
    @GetMapping("/status/{info}")
    public Page<?> findInventoryDtoBySearch(@PathVariable("info") Integer info, @PageableDefault(page = 0, size = 10 )Pageable pageable) {
        return inventoryService.getInventoryListInfo(info, pageable);
    }

    // 불량 조회
    @GetMapping("/disposals")
    public Page<?> getDefectListBySearch(@Param("search") String search, @PageableDefault(page = 0, size = 10 )Pageable pageable) {
        return inventoryService.getDefectList( search, pageable );
    }

    // 불량 처리 상세조회
    @GetMapping("/disposals/{info}")
    public Page<?> findDefectDtoBySearch(@PathVariable("info") Integer info, @PageableDefault(page = 0, size = 10 )Pageable pageable) {
        return inventoryService.getDefectListInfo(info, pageable);
    }

    // 발주 조회
    @GetMapping("/orders")
    public Page<?> getOrdersListBySearch(@Param("search") String search, @PageableDefault(page = 0, size = 10 )Pageable pageable) {
        return inventoryService.getOrdererList( search, pageable );
    }

    // 발주 목록 상세조회
    @GetMapping("/orders/{info}")
    public Page<?> findOrdersDtoBySearch(@PathVariable("info") Integer info, @PageableDefault(page = 0, size = 10 )Pageable pageable) {
        return inventoryService.getOrdererListInfo(info, pageable);
    }

    // 오더 수정 삭제 추가
    @PutMapping("/orders/{orderFormId}")
    public void updateSnoList(@PathVariable("orderFormId") Integer orderFormId, @RequestBody List<OrderPutDTO> dtoList) {
        inventoryService.putOrdererSno(orderFormId, dtoList);
    }

    // 선택용 물품
    @GetMapping("/orders/select/product")
    public Page<?> getProductList ( @PageableDefault(page = 0, size = 10 )Pageable pageable) {
        return inventoryService.getProductCord( pageable);
    }

    // 선택용 거래처
    @GetMapping("/orders/select/vendor")
    public Page<?> getVendorList ( @PageableDefault(page = 0, size = 10 )Pageable pageable) {
        return inventoryService.getVendorCord( pageable);
    }

    // 선택용 창고
    @GetMapping("/orders/select/storage")
    public Page<?> getStorageList ( @PageableDefault(page = 0, size = 10 )Pageable pageable) {
        return inventoryService.getStorageCord( pageable);
    }

    // 인벤토리 선택용
    @GetMapping("/orders/select/inventory")
    public Page<?> getInventoryList (@Param("search") String search, @PageableDefault(page = 0, size = 10 )Pageable pageable) {
        return inventoryService.getInventoryCord(search, pageable);
    }

    // 오더폼 , 오더 추가
    @PostMapping("/orders")
    public void postAddOrderForm( @RequestBody OrderFormPostDTO dtoList) {
        inventoryService.insertOrderForm(dtoList);
    }

    // 오더폼 삭제
    @DeleteMapping("/orders/{orderFormId}")
    public void postAddOrderForm( @PathVariable("orderFormId") Integer orderFormId ) {
        inventoryService.DeleteOrderForm(orderFormId);
    }

    //입고 리스트
    @GetMapping("/inbounds")
    public Page<?> getLogisticsList (Pageable pageable) {
        return inventoryService.getLogisticsList(pageable);
    }

    //입고  상세리스트
    @GetMapping("/inbounds/{logisticsOrderNum}")
    public Page<?> getLogisticsListInfo( @PathVariable("logisticsOrderNum") Integer logisticsOrderNum, Pageable pageable) {
        return inventoryService.getLogisticsListInfo(logisticsOrderNum, pageable);
    }

    //출고 리스트
    @GetMapping("/outbounds")
    public Page<?> getLogisticsOutList (Pageable pageable) {
        return inventoryService.getLogisticsOutList(pageable);
    }

    //출고  상세리스트
    @GetMapping("/outbounds/{logisticsOrderNum}")
    public Page<?> getLogisticsOutListInfo( @PathVariable("logisticsOrderNum") Integer logisticsOrderNum, Pageable pageable) {
        return inventoryService.getLogisticsOutListInfo(logisticsOrderNum, pageable);
    }

    // 입고 처리 ( 오더폼 발주일 수정 => 입고 , 출고 처리 => 재고 추가 => 불량 추가 )
    @PostMapping("/inbounds/{orderformId}")
    public void postBoundsProcess ( @PathVariable("orderformId") Integer orderformId ,  @RequestBody List<LogisticsRequestDTO> dtoList ) {
        inventoryService.processLogisticsAction( orderformId ,  dtoList );
    }

    // 출고처리 , 불량 처리
    @PostMapping("/outbounds")
    public void postOutBoundsProcess ( @RequestBody List<OutLogisticsRequestDTO> dtoList ) {
        inventoryService.processOutLogisticsAction( dtoList );
    }


}
