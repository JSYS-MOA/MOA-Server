package com.moa.server.entity.base.service;
import com.moa.server.entity.salary.AllowanceEntity;
import com.moa.server.entity.salary.AllowanceRepository;
import com.moa.server.entity.user.AdminRoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AllowanceService {
    private final AllowanceRepository repository;

    public List<AllowanceEntity> getList() { return repository.findAll(); }
    public AllowanceEntity getDetail(Integer id) {
        return repository.findById(id).orElse(null);
    }
    public void register(AllowanceEntity data) { repository.save(data); }
    public void modify(AllowanceEntity data) { repository.save(data); } // JPA는 save가 수정도 함
    public void remove(Integer id) { repository.deleteById(id); }

}
