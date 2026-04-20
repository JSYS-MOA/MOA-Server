package com.moa.server.entity.inventory;

import com.moa.server.common.BaseEntity;
import com.moa.server.entity.inventory.dto.InventoryDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "orderform")
@Getter
@Setter // DTO 역할을 위해 세터를 열어줍니다
@NoArgsConstructor // JPA와 DTO 처리를 위한 기본 생성자
@AllArgsConstructor
@Builder
public class OrderformEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderform_id")
    private Integer orderformId;

    @Column(name = "vendor_id")
    private Integer vendorId;

    @Column(name = "orderform_date")
    private LocalDate orderformDate;

    @Column(name = "stock_in_date")
    private LocalDate stockInDate;

    @Column(name = "order_status")
    private String orderStatus;

    //vendor 와 join
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", insertable = false, updatable = false)
    private VendorEntity vendor;

}
