package com.moa.server.entity.approval.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class ApprovaAddDTO {

    private Integer approvaId;
    private String approvaTitle;
    private String approvaContent;
    private Integer writer;
    private Integer approvaKind;
    private String approvaStatus;
    private String approvaMemu;
    private String file;
    private Integer approver;
    private LocalDateTime approvaDate;

}
