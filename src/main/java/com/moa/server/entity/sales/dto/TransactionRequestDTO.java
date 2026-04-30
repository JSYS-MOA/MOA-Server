package com.moa.server.entity.sales.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequestDTO {

    private Integer transactionPrice;
    private String transactionMemo;

}
