package com.moa.server.entity.user.dto;

import com.moa.server.common.BaseEntity;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class AdminCordMapDTO extends BaseEntity {

    private Integer id;
    private String cord;
    private String name;

}
