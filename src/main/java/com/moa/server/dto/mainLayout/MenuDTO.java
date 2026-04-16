package com.moa.server.dto.mainLayout;

import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class MenuDTO {

    private int menuId;
    private String menuTitle;
    private int menuNum;
    private String pagePath;

}
