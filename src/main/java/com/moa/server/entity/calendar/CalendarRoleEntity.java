package com.moa.server.entity.calendar;

import com.moa.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "calender_role")
@Getter
@Setter // DTO 역할을 위해 세터를 열어줍니다
@NoArgsConstructor // JPA와 DTO 처리를 위한 기본 생성자
@AllArgsConstructor
@Builder
public class CalendarRoleEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calender_role_id")
    private Integer calendarRoleId;

    @Column(name = "user_id")  //공유받은 유저
    private Integer userId;

    @Column(name = "calender_id") //공유된 캘린더
    private Integer calendarId;
}