package com.moa.server.entity.salary;

import com.moa.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "company_account")
@Getter
@Setter // DTO 역할을 위해 세터를 열어줍니다
@NoArgsConstructor // JPA와 DTO 처리를 위한 기본 생성자
@AllArgsConstructor
@Builder
public class CompanyAccountEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_account_id")
    private Integer companyAccountId;

    @Column(name = "company_account_cord")
    private String companyAccountCord;

    @Column(name = "company_account_name")
    private String companyAccountName;

    @Column(name = "company_account_bank")
    private String companyAccountBank;

    @Column(name = "company_account_num")
    private Integer companyAccountNum;

    @Column(name = "company_account_is_use")
    private Integer companyAccountIsUse;
}