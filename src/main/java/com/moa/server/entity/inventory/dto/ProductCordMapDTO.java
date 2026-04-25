package com.moa.server.entity.inventory.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class ProductCordMapDTO {

    private Integer productId;
    private String productCord;
    private String productName;
    private Integer productPrice;

}
