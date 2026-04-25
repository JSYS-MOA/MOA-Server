package com.moa.server.entity.inventory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OutLogisticsRequestDTO {

    private List<OutLogisticsItemDTO> items;

    @Getter
    @Setter
    public static class OutLogisticsItemDTO {

        @JsonProperty("logisticsType")
        private String logisticsType;

        @JsonProperty("stockInDate")
        private LocalDate stockInDate;

        @JsonProperty("userId")
        private Integer userId;

        @JsonProperty("storageId")
        private Integer storageId;

        @JsonProperty("productId")
        private Integer productId;

        @JsonProperty("inventoryId")
        private Integer inventoryId;

        @JsonProperty("logisticSno")
        private Integer logisticSno;

        @JsonProperty("defectSno")
        private Integer defectSno;

        @JsonProperty("productPrice")
        private Integer productPrice;

        @JsonProperty("expirationDate")
        private LocalDateTime expirationDate;

        @JsonProperty("memo")
        private String memo;

        @JsonProperty("defectStatus")
        private String defectStatus;

        @JsonProperty("disposalDate")
        private LocalDateTime disposalDate;
    }
}

