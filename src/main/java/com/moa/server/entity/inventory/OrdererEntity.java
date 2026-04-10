package com.moa.server.entity.inventory;

import com.moa.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "`order`")
@Getter
@Setter // DTO 역할을 위해 세터를 열어줍니다
@NoArgsConstructor // JPA와 DTO 처리를 위한 기본 생성자
@AllArgsConstructor
@Builder
public class OrdererEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderer_id")
    private Integer ordererId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "orderform_id")
    private Integer orderformId;

    @Column(name = "order_sno")
    private Integer orderSno;

    @Column(name = "unit_price")
    private Integer unitPrice;

}
