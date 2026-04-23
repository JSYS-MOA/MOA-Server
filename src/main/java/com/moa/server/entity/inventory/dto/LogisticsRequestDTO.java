package com.moa.server.entity.inventory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class LogisticsRequestDTO {
    @JsonProperty("logisticsType")
    private String logisticsType;

    @JsonProperty("orderformId")
    private Integer orderformId;

    @JsonProperty("stockInDate")
    private LocalDate stockInDate;

    @JsonProperty("userId")
    private Integer userId;

    private List<LogisticsItemDTO> items;

    @Getter
    @Setter
    public static class LogisticsItemDTO {
        @JsonProperty("productId")
        private Integer productId;

        @JsonProperty("storageId")
        private Integer storageId;

        @JsonProperty("logisticSno")
        private Integer logisticSno;

        @JsonProperty("defectSno")
        private Integer defectSno;

        @JsonProperty("price")
        private Integer price;

        @JsonProperty("expirationDate")
        private LocalDateTime expirationDate;

        @JsonProperty("memo")
        private String memo;

        @JsonProperty("inventoryId")
        private Integer inventoryId;

        @JsonProperty("defectStatus")
        private String defectStatus;

        @JsonProperty("disposalDate")
        private LocalDateTime disposalDate;
    }
}

