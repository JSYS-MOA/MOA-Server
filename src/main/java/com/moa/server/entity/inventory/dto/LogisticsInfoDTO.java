package com.moa.server.entity.inventory.dto;

import com.moa.server.entity.inventory.ProductEntity;
import com.moa.server.entity.inventory.StorageEntity;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class LogisticsInfoDTO {

    private Integer logisticsId;
    private Integer productId;
    private Integer storageId;

    // logisticsEntity에서 가져올 필드
    private Integer logisticsOrderNum;
    private String logisticsType;
    private LocalDate logisticDate;
    private Integer logisticSno;
    private Integer logisticsPrice;

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
