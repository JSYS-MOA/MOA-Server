package com.moa.server.entity.layout.dto;

import com.moa.server.entity.menu.MenuEntity;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LayoutDTO {

    private String userName;
    private String employeeId;
    private String departmentName;
    private String gradeName;

    private List<Map<String, Object>> menuList;

}
