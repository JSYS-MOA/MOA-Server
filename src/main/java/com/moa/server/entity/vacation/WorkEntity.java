package com.moa.server.entity.vacation;

import com.moa.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "work")
@Getter
@Setter // DTO 역할을 위해 세터를 열어줍니다
@NoArgsConstructor // JPA와 DTO 처리를 위한 기본 생성자
@AllArgsConstructor
@Builder
public class WorkEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_id")
    private Integer workId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "work_date")
    private LocalDate workDate;

    @Column(name = "start_work")
    private LocalDateTime startWork;

    @Column(name = "finish_work")
    private LocalDateTime finishWork;

    @Column(name = "work_status")
    private String workStatus;

    @Column(name = "work_memo")
    private String workMemo;
}
