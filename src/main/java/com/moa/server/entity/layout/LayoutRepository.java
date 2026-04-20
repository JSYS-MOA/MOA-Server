package com.moa.server.entity.layout;

import com.moa.server.entity.menu.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LayoutRepository extends JpaRepository<MenuEntity,Integer> {

}
