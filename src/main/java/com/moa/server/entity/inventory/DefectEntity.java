package com.moa.server.entity.inventory;

import com.moa.server.common.BaseEntity;
import com.moa.server.entity.inventory.dto.DefectDTO;
import com.moa.server.entity.inventory.dto.InventoryDTO;
import com.moa.server.entity.user.UserEntity;
import com.moa.server.entity.user.dto.LoginResponseDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "defect")
@Getter
@Setter // DTO 역할을 위해 세터를 열어줍니다
@NoArgsConstructor // JPA와 DTO 처리를 위한 기본 생성자
@AllArgsConstructor
@Builder
public class DefectEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "defect_id")
    private Integer defectId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "inventory_id")
    private Integer inventoryId;

    @Column(name = "defect_sno")
    private Integer defectSno;

    @Column(name = "defect_status")
    private String defectStatus;

    @Column(name = "req_date")
    private LocalDateTime reqDate;

    @Column(name = "disposal_date")
    private LocalDateTime disposalDate;

    @Column(name = "defect_memo")
    private String defectMemo;

    //user 와 join
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;


    //inventory 와 join
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id", insertable = false, updatable = false)
    private InventoryEntity inventory;

    // InventoryEntity.java 내부 혹은 별도 Mapper
    public DefectDTO toDTO() {
        return DefectDTO.builder()
                .defectId(this.defectId)
                .userId(this.userId)
                .inventoryId(this.inventoryId)
                .defectSno(this.defectSno)
                .defectStatus(this.defectStatus)
                .reqDate(this.reqDate)
                .disposalDate(this.disposalDate)
                .defectMemo(this.defectMemo)
                .inventorySno(this.inventory != null ? this.inventory.getInventorySno() : null)
                .productCord(this.inventory.getProduct() != null ? this.inventory.getProduct().getProductCord() : null)
                .productName(this.inventory.getProduct() != null ? this.inventory.getProduct().getProductName() : null)
                .productPrice(this.inventory.getProduct() != null ? this.inventory.getProduct().getProductPrice() : null)
                .storageName(this.inventory.getStorage() != null ? this.inventory.getStorage().getStorageName() : null)
                .userName(this.user != null ? this.user.getUserName() : null)
                .employeeId(this.user != null ? this.user.getEmployeeId() : null)
                .build();
    }



}
