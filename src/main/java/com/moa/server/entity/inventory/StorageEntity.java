package com.moa.server.entity.inventory;

import com.moa.server.common.BaseEntity;
import com.moa.server.entity.inventory.dto.StorageCordMapDTO;
import com.moa.server.entity.inventory.dto.VendorCordMapDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "storage")
@Getter
@Setter // DTO 역할을 위해 세터를 열어줍니다
@NoArgsConstructor // JPA와 DTO 처리를 위한 기본 생성자
@AllArgsConstructor
@Builder
public class StorageEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storage_id")
    private Integer storageId;

    @Column(name = "storage_cord")
    private String storageCord;

    @Column(name = "storage_name")
    private String storageName;

    @Column(name = "storage_is_use")
    private Integer storageIsUse;

    @Column(name = "storage_address")
    private String storageAddress;

    public StorageCordMapDTO toDTO() {
        return StorageCordMapDTO.builder()
                .storageId(this.storageId)
                .storageCord(this.storageCord)
                .storageName(this.storageName)
                .storageAddress(this.storageAddress)
                .build();
    }


}
