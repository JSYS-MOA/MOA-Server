package com.moa.server.entity.inventory.dto;

import com.moa.server.common.BaseEntity;
import com.moa.server.entity.inventory.ProductEntity;
import com.moa.server.entity.inventory.StorageEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "logistics")
@Getter
@Setter // DTO 역할을 위해 세터를 열어줍니다
@NoArgsConstructor // JPA와 DTO 처리를 위한 기본 생성자
@AllArgsConstructor
@Builder
public class LogisticsRequestDTO extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "logistics_id")
    private Integer logisticsId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "storage_id")
    private Integer storageId;

    @Column(name = "logistics_order_num")
    private Integer logisticsOrderNum;

    @Column(name = "logistics_type")
    private String logisticsType;

    @Column(name = "logistic_date")
    private LocalDate logisticDate;

    @Column(name = "logistic_sno")
    private Integer logisticSno;

    @Column(name = "logistics_price")
    private Integer logisticsPrice;

    //productEntity 와 join
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private ProductEntity product;

    //storageEntity 와 join
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storage_id", insertable = false, updatable = false)
    private StorageEntity storage;

    public LogisticsInfoDTO toDTO() {
        return LogisticsInfoDTO.builder()
                .logisticsId(this.logisticsId)
                .productId(this.productId)
                .storageId(this.storageId)
                .logisticsOrderNum(this.logisticsOrderNum)
                .logisticsType(this.logisticsType)
                .logisticDate(this.logisticDate)
                .logisticSno(this.logisticSno)
                .logisticsPrice(this.logisticsPrice)
                .productCord(this.product != null ? this.product.getProductCord() : null)
                .productName(this.product != null ? this.product.getProductName() : null)
                .productPrice(this.product != null ? this.product.getProductPrice() : null)
                .storageCord(this.storage != null ? this.storage.getStorageCord() : null)
                .storageName(this.storage != null ? this.storage.getStorageName() : null)
                .build();
    }


}