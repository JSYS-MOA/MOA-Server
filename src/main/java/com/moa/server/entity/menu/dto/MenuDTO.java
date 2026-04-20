package com.moa.server.entity.menu.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class MenuDTO {

    private Integer menuId;
    private String menuTitle;
    private Integer menuNum;
    private String pagePath;

}
