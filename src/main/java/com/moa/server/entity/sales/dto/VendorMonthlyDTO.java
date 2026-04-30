package com.moa.server.entity.sales.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class VendorMonthlyDTO {

        private String vendorCode;
        private String vendorName;
        private List<Long> monthly;
        private Long total;

}
