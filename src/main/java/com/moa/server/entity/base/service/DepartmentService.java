package com.moa.server.entity.base.service;
import com.moa.server.entity.user.AdminRoleEntity;
import com.moa.server.entity.user.DepartmentEntity;
import com.moa.server.entity.user.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository repository;

    public List<DepartmentEntity> getList() { return repository.findAll(); }
    public DepartmentEntity getDetail(Integer id) {
        return repository.findById(id).orElse(null);
    }
    public void register(DepartmentEntity data) { repository.save(data); }
    public void modify(DepartmentEntity data) { repository.save(data); } // JPA는 save가 수정도 함
    public void remove(Integer id) { repository.deleteById(id); }
}
