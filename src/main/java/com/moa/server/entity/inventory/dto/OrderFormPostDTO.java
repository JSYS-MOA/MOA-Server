package com.moa.server.entity.inventory.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class OrderFormPostDTO {

    private LocalDate orderformDate;
    private LocalDate stockInDate;
    private Integer vendorId;
    private String orderStatus;
    private List<OrderPutDTO> items;

}
