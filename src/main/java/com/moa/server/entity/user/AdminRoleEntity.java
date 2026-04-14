package com.moa.server.entity.user;

import com.moa.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "admin_role")
@Getter
@Setter // DTO 역할을 위해 세터를 열어줍니다
@NoArgsConstructor // JPA와 DTO 처리를 위한 기본 생성자
@AllArgsConstructor
@Builder
public class AdminRoleEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_role_id")
    private Integer id;

    @Column(name = "admin_role_code")
    private String code;

    @Column(name = "admin_role_name")
    private String name;

    @Column(name = "admin_role_is_use")
    private Integer isUse;

    // 조인
    @OneToMany(mappedBy = "role") // AdminUser의 'role' 필드에 의해 매핑됨을 명시
    private List<UserEntity> users = new ArrayList<>();

}
