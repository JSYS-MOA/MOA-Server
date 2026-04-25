package com.moa.server.entity.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// 재고조회
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryCordMapDTO {

    private Integer inventoryId;
    private Integer productId;
    private Integer storageId;

    private Integer inventorySno;

    private Integer logisticSno;
    private LocalDateTime expirationDate;
    private Integer logisticsId;

    private String productName;
    private String productCord;
    private Integer productPrice;

    private String storageCord;
    private String storageName;


}
