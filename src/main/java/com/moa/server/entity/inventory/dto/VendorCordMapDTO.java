package com.moa.server.entity.inventory.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class VendorCordMapDTO {

    private Integer vendorId;
    private String vendorCord;
    private String vendorName;

}
