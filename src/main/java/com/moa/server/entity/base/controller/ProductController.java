package com.moa.server.entity.base.controller;

import com.moa.server.entity.base.service.AllowanceService;
import com.moa.server.entity.base.service.ProductService;
import com.moa.server.entity.inventory.ProductEntity;
import com.moa.server.entity.salary.AllowanceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/base/item")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping
    public List<ProductEntity> list() {
        return service.getList();
    }
    @GetMapping("/{product_id}")
    public ProductEntity detail(@PathVariable Integer id) {
        return service.getDetail(id);
    }
    @PostMapping
    public void add(@RequestBody ProductEntity data) {
        service.register(data);
    }
    @PutMapping("/{product_id}")
    public void update(@RequestBody ProductEntity data) {
        service.modify(data);
    }
    @DeleteMapping("/{product_id}")
    public void delete(@PathVariable Integer id) {
        service.remove(id);
    }
}
