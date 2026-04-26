package com.moa.server.entity.inventory.dto;

import com.moa.server.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO extends BaseEntity {

    private Integer ordererId;
    private Integer productId;
    private Integer orderformId;
    private Integer orderSno;
    private Integer unitPrice;

    //  물품코드
    private String productCord;
    // 품목명
    private String productName;
    //입고단가
    private Integer productPrice;


    private LocalDate orderformDate;
    private LocalDate stockInDate;
    private String orderStatus;

    private Integer vendorId;
    private String vendorCord;
    private String vendorName;

    private Integer totalOrderSno;
    private Integer totalUnitPrice;
    private Integer totalPrice;

}
