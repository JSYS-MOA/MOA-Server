package com.moa.server.entity.layout.service;

import com.moa.server.entity.layout.dao.LayoutDAO;
import com.moa.server.entity.layout.dto.LayoutDTO;
import com.moa.server.entity.layout.dto.MenuDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LayoutService {

    private final LayoutDAO layoutDAO;

    public LayoutDTO getLayout(String employeeId) {

        // 1. 유저 정보 조회
        Object[] user = layoutDAO.userLayoutInfo(employeeId);

        if (user == null) {
            return null;
        }

        // 2. 메뉴 리스트 조회 + DTO 변환
        List<MenuDTO> ml = null;
        ml = layoutDAO.findAllMenus().stream()
                .map(m -> MenuDTO.builder()
                        .menuId(m.getMenuId())
                        .menuTitle(m.getMenuTitle())
                        .menuNum(m.getMenuNum())
                        .pagePath(m.getPagePath())
                        .build()
                )
                .toList();

        // 3. LayoutDTO 조립해서 반환
        return LayoutDTO.builder()
                .userName((String) user[1])
                .employeeId((String) user[4])
                .departmentName((String) user[2])
                .gradeName((String) user[3])
                .menuList(ml)
                .build();
    }
}
