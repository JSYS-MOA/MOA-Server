package com.moa.server.entity.approval.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class ApprovaLineCordMapDTO {

    private Integer approvalLineId;
    private String approvalLineCord;
    private Integer approvalLineUser;
    private String approvalLineName;
}
