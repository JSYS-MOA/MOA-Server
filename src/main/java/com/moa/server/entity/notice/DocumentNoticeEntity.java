package com.moa.server.entity.notice;

import com.moa.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "document_notice")
@Getter
@Setter // DTO 역할을 위해 세터를 열어줍니다
@NoArgsConstructor // JPA와 DTO 처리를 위한 기본 생성자
@AllArgsConstructor
@Builder
public class DocumentNoticeEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_notice_id")
    private Integer documentNoticeId;

    @Column(name = "document_notice_title")
    private String documentNoticeTitle;

    @Column(name = "writer")
    private Integer writer;

    @Column(name = "file")
    private String file;
}
