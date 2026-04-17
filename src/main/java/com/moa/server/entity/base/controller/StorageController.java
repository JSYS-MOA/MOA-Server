package com.moa.server.entity.base.controller;

import com.moa.server.entity.base.service.AllowanceService;
import com.moa.server.entity.base.service.StorageService;
import com.moa.server.entity.inventory.StorageEntity;
import com.moa.server.entity.salary.AllowanceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/base/whse")
@RequiredArgsConstructor
public class StorageController {
    private final StorageService service;

    @GetMapping
    public List<StorageEntity> list() {
        return service.getList();
    }
    @GetMapping("/{storage_id}")
    public StorageEntity detail(@PathVariable Integer id) {
        return service.getDetail(id);
    }
    @PostMapping
    public void add(@RequestBody StorageEntity data) {
        service.register(data);
    }
    @PutMapping("/{storage_id}")
    public void update(@RequestBody StorageEntity data) {
        service.modify(data);
    }
    @DeleteMapping("/{storage_id}")
    public void delete(@PathVariable Integer id) {
        service.remove(id);
    }
}
