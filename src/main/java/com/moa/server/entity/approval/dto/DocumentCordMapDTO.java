package com.moa.server.entity.approval.dto;

import com.moa.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class DocumentCordMapDTO extends BaseEntity {

    private Integer documentId;
    private String documentCord;
    private String documentName;

}
