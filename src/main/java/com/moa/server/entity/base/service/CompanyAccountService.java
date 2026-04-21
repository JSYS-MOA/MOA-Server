package com.moa.server.entity.base.service;
import com.moa.server.entity.salary.CompanyAccountEntity;
import com.moa.server.entity.salary.CompanyAccountRepository;
import com.moa.server.entity.user.AdminRoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyAccountService {
    private final CompanyAccountRepository repository;

    public List<CompanyAccountEntity> getList() { return repository.findAll(); }
    public CompanyAccountEntity getDetail(Integer companyAccountId) {
        return repository.findById(companyAccountId).orElse(null);
    }
    public void register(CompanyAccountEntity data) { repository.save(data); }
    public void modify(CompanyAccountEntity data) { repository.save(data); } // JPA는 save가 수정도 함
    public void remove(Integer companyAccountId) { repository.deleteById(companyAccountId); }
}
