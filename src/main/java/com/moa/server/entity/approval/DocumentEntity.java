package com.moa.server.entity.approval;

import com.moa.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "document")
@Getter
@Setter // DTO 역할을 위해 세터를 열어줍니다
@NoArgsConstructor // JPA와 DTO 처리를 위한 기본 생성자
@AllArgsConstructor
@Builder
public class DocumentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Integer documentId;

    @Column(name = "document_cord")
    private String documentCord;

    @Column(name = "document_name")
    private String documentName;

    @Column(name = "document_is_use")
    private Integer documentIsUse;



}
