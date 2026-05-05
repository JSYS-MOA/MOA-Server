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

//     1. 유저 정보 조회
       Object[] user = layoutDAO.userLayoutInfo(employeeId);

        // 2. 메뉴 리스트 조회 + DTO 변환
        List<MenuEntity> menuList = repository.findAll().stream()
                .map(m -> MenuEntity.builder()
                        .menuId(m.getMenuId())
                        .menuTitle(m.getMenuTitle())
                        .menuNum(m.getMenuNum())
                        .pagePath(m.getPagePath())
                        .build())
                .toList();

        // 3. [핵심] 유저 데이터가 없을 경우 방어 코드
        if (user == null) {
            return LayoutDTO.builder()
                    .userName("정보 없음")
                    .employeeId(employeeId)
                    .departmentName("-")
                    .gradeName("-")
                    .menuList(menuList)
                    .build();
        }

            return LayoutDTO.builder()
                    .userName(user[1] != null ? String.valueOf(user[1]) : "이름없음")
                    .departmentName(user[2] != null ? String.valueOf(user[2]) : "부서미정")
                    .gradeName(user[3] != null ? String.valueOf(user[3]) : "직급미정")
                    .employeeId(user[4] != null ? String.valueOf(user[4]) : employeeId)
                    .menuList(menuList)
                    .build();

    }
}
