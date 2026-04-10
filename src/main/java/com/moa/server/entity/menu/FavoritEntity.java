package com.moa.server.entity.menu;

import com.moa.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "favorit")
@Getter
@Setter // DTO 역할을 위해 세터를 열어줍니다
@NoArgsConstructor // JPA와 DTO 처리를 위한 기본 생성자
@AllArgsConstructor
@Builder
public class FavoritEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorit_id")
    private Integer favoritId;

    @Column(name = "menu_id")
    private Integer menuId;

    @Column(name = "user_id")
    private Integer userId;
}
