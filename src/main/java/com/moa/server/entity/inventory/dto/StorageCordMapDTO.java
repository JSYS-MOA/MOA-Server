package com.moa.server.entity.inventory.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class StorageCordMapDTO {

    private Integer storageId;
    private String storageCord;
    private String storageName;
    private String storageAddress;

}
