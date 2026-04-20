package com.moa.server.entity.base.service;

import com.moa.server.entity.inventory.VendorEntity;
import com.moa.server.entity.inventory.VendorRepository;
import com.moa.server.entity.user.AdminRoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VendorService {
    private final VendorRepository repository;

    public List<VendorEntity> getList() { return repository.findAll(); }
    public VendorEntity getDetail(Integer id) {
        return repository.findById(id).orElse(null);
    }
    public void register(VendorEntity data) { repository.save(data); }
    public void modify(VendorEntity data) { repository.save(data); } // JPA는 save가 수정도 함
    public void remove(Integer id) { repository.deleteById(id); }
}
