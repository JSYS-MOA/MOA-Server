package com.moa.server.entity.layout.dto;

import com.moa.server.entity.layout.dto.MenuDTO;
import lombok.*;

import java.util.List;

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

    private List<MenuDTO> menuList;

}
