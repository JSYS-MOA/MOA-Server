package com.moa.server.entity.inventory;

import com.moa.server.common.BaseEntity;
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
public class LogisticsEntity extends BaseEntity {
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
}