package com.moa.server.entity.base.controller;

import com.moa.server.entity.base.service.AllowanceService;
import com.moa.server.entity.base.service.VendorService;
import com.moa.server.entity.inventory.VendorEntity;
import com.moa.server.entity.salary.AllowanceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/base/partner")
@RequiredArgsConstructor
public class VendorController {
    private final VendorService service;

    @GetMapping
    public List<VendorEntity> list() {
        return service.getList();
    }
    @GetMapping("/{vendor_id}")
    public VendorEntity detail(@PathVariable Integer id) {
        return service.getDetail(id);
    }
    @PostMapping
    public void add(@RequestBody VendorEntity data) {
        service.register(data);
    }
    @PutMapping("/{vendor_id}")
    public void update(@RequestBody VendorEntity data) {
        service.modify(data);
    }
    @DeleteMapping("/{vendor_id}")
    public void delete(@PathVariable Integer id) {
        service.remove(id);
    }
}
