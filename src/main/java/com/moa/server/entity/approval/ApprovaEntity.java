package com.moa.server.entity.approval;

import com.moa.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "approva")
@Getter
@Setter // DTO 역할을 위해 세터를 열어줍니다
@NoArgsConstructor // JPA와 DTO 처리를 위한 기본 생성자
@AllArgsConstructor
@Builder
public class ApprovaEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "approva_id")
    private Integer approvaId;

    @Column(name = "approva_title")
    private String approvaTitle;

    @Column(name = "approva_content")
    private String approvaContent;

    @Column(name = "writer")
    private Integer writer;

    @Column(name = "approva_kind")
    private Integer approvaKind;

    @Column(name = "approva_status")
    private String approvaStatus;

    @Column(name = "approva_memu")
    private String approvaMemu;

    @Column(name = "file")
    private String file;

    @Column(name = "approver")
    private Integer approver;

    @Column(name = "approva_date")
    private LocalDateTime approvaDate;
}
