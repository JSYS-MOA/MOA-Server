package com.moa.server.entity.base.service;
import com.moa.server.entity.inventory.ProductEntity;
import com.moa.server.entity.inventory.ProductRepository;
import com.moa.server.entity.user.AdminRoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public List<ProductEntity> getList() { return repository.findAll(); }
    public ProductEntity getDetail(Integer productId) {
        return repository.findById(productId).orElse(null);
    }
    public void register(ProductEntity data) { repository.save(data); }
    public void modify(ProductEntity data) { repository.save(data); } // JPA는 save가 수정도 함
    public void remove(Integer productId) { repository.deleteById(productId); }
}
