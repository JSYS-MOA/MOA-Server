package com.moa.server.entity.user;

import com.moa.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "admin_role_cord")
    private String cord;

    @Column(name = "admin_role_name")
    private String name;

    @Column(name = "admin_role_is_use")
    private Integer isUse;

}
