package com.moa.server.entity.menu.service;

import com.moa.server.entity.menu.FavoritRepository;
import com.moa.server.entity.menu.MenuRepository;
import com.moa.server.entity.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuService {
    private final UserRepository userRepository;

    private final MenuRepository menuRepository;
    private final FavoritRepository favoritRepository;

    // 공지사항 게시, 시스템 메뉴 관리, 즐겨찾기(Favorite) 처리
}
