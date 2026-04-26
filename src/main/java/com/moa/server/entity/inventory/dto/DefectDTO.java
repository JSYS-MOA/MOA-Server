package com.moa.server.entity.inventory.dto;

import com.moa.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
// 불량퍠기
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DefectDTO extends BaseEntity {


    private Integer defectId;
    private Integer userId;
    private Integer inventoryId;

    private Integer defectSno;

    private String defectStatus;

    private LocalDateTime reqDate;

    private LocalDateTime disposalDate;

    private String defectMemo;


    private Integer inventorySno;

    //인벤토리 안 물품
    //  물품코드
    private String productCord;
    // 품목명
    private String productName;
    //입고단가
    private Integer productPrice;

    //user
    private String userName;
    private String employeeId;

    // 인벤토리 안 창고명
    private String storageName;
}
