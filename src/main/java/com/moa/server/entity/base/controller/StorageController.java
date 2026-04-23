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
    @GetMapping("/{storageId}")
    public StorageEntity detail(@PathVariable Integer storageId) {
        return service.getDetail(storageId);
    }
    @PostMapping
    public void add(@RequestBody StorageEntity data) {
        service.register(data);
    }
    @PutMapping("/{storageId}")
    public void update(@RequestBody StorageEntity data) {
        service.modify(data);
    }
    @DeleteMapping("/{storageId}")
    public void delete(@PathVariable Integer storageId) {
        service.remove(storageId);
    }
}
