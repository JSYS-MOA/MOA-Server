package com.moa.server.entity.layout.service;

import com.moa.server.entity.layout.LayoutRepository;
import com.moa.server.entity.layout.dao.LayoutDAO;
import com.moa.server.entity.layout.dto.LayoutDTO;
import com.moa.server.entity.menu.MenuEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LayoutService {

    private final LayoutDAO layoutDAO;
    private final LayoutRepository repository;

    public LayoutDTO getLayout(String employeeId) {

        // 1. 유저 정보 조회
        Object[] user = layoutDAO.userLayoutInfo(employeeId);

        if (user == null) {
            return LayoutDTO.builder()
                    .userName("정보 없음")
                    .employeeId(employeeId)
                    .menuList(new ArrayList<>())
                    .build();
        }

        // 2. 메뉴 리스트 조회 + DTO 변환
        List<MenuEntity> menuList = repository.findAll().stream()
                .map(m -> MenuEntity.builder()
                        .menuId(m.getMenuId())
                        .menuTitle(m.getMenuTitle())
                        .menuNum(m.getMenuNum())
                        .pagePath(m.getPagePath())
                        .build())
                .toList();

        return LayoutDTO.builder()
                .userName(user[1] != null ? String.valueOf(user[1]) : "")
                .departmentName(user[2] != null ? String.valueOf(user[2]) : "")
                .gradeName(user[3] != null ? String.valueOf(user[3]) : "")
                .employeeId(user[4] != null ? String.valueOf(user[4]) : employeeId)
                .menuList(menuList)
                .build();

    }
}
