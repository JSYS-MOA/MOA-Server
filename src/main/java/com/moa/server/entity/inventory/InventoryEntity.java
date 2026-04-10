package com.moa.server.entity.inventory;

import com.moa.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory")
@Getter
@Setter // DTO 역할을 위해 세터를 열어줍니다
@NoArgsConstructor // JPA와 DTO 처리를 위한 기본 생성자
@AllArgsConstructor
@Builder
public class InventoryEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Integer inventoryId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "storage_id")
    private Integer storageId;

    @Column(name = "inventory_sno")
    private Integer inventorySno;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "inventory_memo")
    private String inventoryMemo;

    @Column(name = "logistics_id")
    private Integer logisticsId;
}
