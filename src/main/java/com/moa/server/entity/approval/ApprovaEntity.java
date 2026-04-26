package com.moa.server.entity.approval;

import com.moa.server.common.BaseEntity;
import com.moa.server.entity.approval.dto.ApprovaUserDTO;
import com.moa.server.entity.inventory.dto.InventoryCordMapDTO;
import com.moa.server.entity.user.AdminRoleEntity;
import com.moa.server.entity.user.UserEntity;
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

    //ApprovalLineEntity 와 join
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approva_kind", insertable = false, updatable = false, referencedColumnName = "approval_line_id")
    private ApprovalLineEntity Line;

    //UserEntity 와 join
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer", insertable = false, updatable = false, referencedColumnName = "user_id")
    private UserEntity UserWriter;

    //UserEntity 와 join
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approver", insertable = false, updatable = false, referencedColumnName = "user_id")
    private UserEntity UserApprover;

    public ApprovaUserDTO MapDTO() {
        return ApprovaUserDTO.builder()
                .approvaId(this.approvaId)
                .approvaTitle(this.approvaTitle)
                .approvaContent(this.approvaContent)
                .approvaKind(this.approvaKind)
                .approvaStatus(this.approvaStatus)
                .approvaMemu(this.approvaMemu)
                .file(this.file)
                .approvaDate(this.approvaDate)
                .writerInfo(this.UserWriter != null ? createUserDetail(this.UserWriter) : null)
                .approverInfo(this.UserApprover != null ? createUserDetail(this.UserApprover) : null)
                .build();
    }

    private ApprovaUserDTO.UserInfo createUserDetail(UserEntity user) {
        return ApprovaUserDTO.UserInfo.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .employeeId(user.getEmployeeId())
                .phone(user.getPhone())
                .email(user.getEmail())
                .roleName(user.getRole() != null ? user.getRole().getName() : null)
                .roleCode(user.getRole() != null ? user.getRole().getCord() : null)
                .gradeName(user.getGrade() != null ? user.getGrade().getGradeName() : null)
                .gradeCord(user.getGrade() != null ? user.getGrade().getGradeCord() : null)
                .departmentName(user.getDepartment() != null ? user.getDepartment().getDepartmentName() : null)
                .departmentCord(user.getDepartment() != null ? user.getDepartment().getDepartmentCord() : null)
                .build();
    }



}
