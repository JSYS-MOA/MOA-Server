package com.moa.server.entity.notice;

import com.moa.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notice")
@Getter
@Setter // DTO 역할을 위해 세터를 열어줍니다
@NoArgsConstructor // JPA와 DTO 처리를 위한 기본 생성자
@AllArgsConstructor
@Builder
public class NoticeEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Integer noticeId;

    @Column(name = "notice_title")
    private String noticeTitle;

    @Column(name = "notice_content")
    private String noticeContent;

    @Column(name = "writer")
    private Integer writer;

    @Column(name = "notice_type")
    private String noticeType;

    @Column(name = "file")
    private String file;

    @Column(name = "post_date")
    private LocalDateTime postDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "is_notice")
    private Boolean isNotice;
}
