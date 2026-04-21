package com.moa.server.entity.inventory.dto;

import com.moa.server.common.BaseEntity;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class OrderPutDTO {

    private Integer ordererId;
    private Integer orderSno;
    private Integer productId;
    private Integer orderformId;
    private Integer unitPrice;

}
