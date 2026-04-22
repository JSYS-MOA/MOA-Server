package com.moa.server.entity.inventory;

import com.moa.server.common.BaseEntity;
import com.moa.server.entity.inventory.dto.ProductCordMapDTO;
import com.moa.server.entity.inventory.dto.VendorCordMapDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vendor")
@Getter
@Setter // DTO 역할을 위해 세터를 열어줍니다
@NoArgsConstructor // JPA와 DTO 처리를 위한 기본 생성자
@AllArgsConstructor
@Builder
public class VendorEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_id")
    private Integer vendorId;

    @Column(name = "vendor_cord")
    private String vendorCord;

    @Column(name = "vendor_name")
    private String vendorName;

    @Column(name = "vendor_is_use")
    private Integer vendorIsUse;

    public VendorCordMapDTO toDTO() {
        return VendorCordMapDTO.builder()
                .vendorId(this.vendorId)
                .vendorCord(this.vendorCord)
                .vendorName(this.vendorName)
                .build();
    }
}
