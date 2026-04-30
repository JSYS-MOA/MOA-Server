package com.moa.server.entity.sales.dto;

import com.moa.server.entity.inventory.dto.OrderDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TaxInvoiceResponseDTO {

    //공급자(모아)
    private String supplierName;
    private String supplierCode;

    //거래처
    private String receiverName;
    private String receiverCode;

    //전표정보
    private Integer transactionId;
    private String transactionDate;
    private Integer supplyPrice;
    private Integer tax;
    private Integer totalPrice;

    //품목목록
    private List<OrderDTO> items;

    }

