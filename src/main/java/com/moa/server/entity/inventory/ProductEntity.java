package com.moa.server.entity.inventory;

import com.moa.server.common.BaseEntity;
import com.moa.server.entity.inventory.dto.InventoryDTO;
import com.moa.server.entity.inventory.dto.ProductCordMapDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Getter
@Setter // DTO 역할을 위해 세터를 열어줍니다
@NoArgsConstructor // JPA와 DTO 처리를 위한 기본 생성자
@AllArgsConstructor
@Builder
public class ProductEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_cord")
    private String productCord;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_is_use")
    private Integer productIsUse;

    @Column(name = "product_price")
    private Integer productPrice;

    @Column(name = "product_category")
    private String productCategory;

    public ProductCordMapDTO toDTO() {
        return ProductCordMapDTO.builder()
                .productId(this.productId)
                .productCord(this.productCord)
                .productName(this.productName)
                .productPrice(this.productPrice)
                .build();
    }

}
