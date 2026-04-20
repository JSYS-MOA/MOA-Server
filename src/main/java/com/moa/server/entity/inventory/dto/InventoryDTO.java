package com.moa.server.entity.inventory.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryDTO {

    private Integer inventoryId;
    private Integer productId;
    private Integer storageId;
    //총재고수량
    private Integer inventorySno;
    private LocalDateTime expirationDate;
    private String inventoryMemo;
    private Integer logisticsId;

    // productEntity에서 가져올 필드
    // 물품코드
    private String productCord;
    // 품목명
    private String productName;
    //입고단가
    private Integer productPrice;

    // storageEntity에서 가져올 필드
    private String storageCord;
    //창고명
    private String storageName;

    

}
