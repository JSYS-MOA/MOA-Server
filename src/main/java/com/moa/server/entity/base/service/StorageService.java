package com.moa.server.entity.base.service;
import com.moa.server.entity.inventory.StorageEntity;
import com.moa.server.entity.inventory.StorageRepository;
import com.moa.server.entity.user.AdminRoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StorageService {
    private final StorageRepository repository;

    public List<StorageEntity> getList() { return repository.findAll(); }
    public StorageEntity getDetail(Integer id) {
        return repository.findById(id).orElse(null);
    }
    public void register(StorageEntity data) { repository.save(data); }
    public void modify(StorageEntity data) { repository.save(data); } // JPA는 save가 수정도 함
    public void remove(Integer id) { repository.deleteById(id); }
}
