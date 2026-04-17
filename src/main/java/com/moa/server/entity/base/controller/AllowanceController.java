package com.moa.server.entity.base.controller;

import com.moa.server.entity.base.service.AdminRoleService;
import com.moa.server.entity.base.service.AllowanceService;
import com.moa.server.entity.salary.AllowanceEntity;
import com.moa.server.entity.user.AdminRoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/base/allow")
@RequiredArgsConstructor
public class AllowanceController {
    private final AllowanceService service;

    @GetMapping
    public List<AllowanceEntity> list() {
        return service.getList();
    }
    @GetMapping("/{allowance_id}")
    public AllowanceEntity detail(@PathVariable Integer id) {
        return service.getDetail(id);
    }
    @PostMapping
    public void add(@RequestBody AllowanceEntity data) {
        service.register(data);
    }
    @PutMapping("/{allowance_id}")
    public void update(@RequestBody AllowanceEntity data) {
        service.modify(data);
    }
    @DeleteMapping("/{allowance_id}")
    public void delete(@PathVariable Integer id) {
        service.remove(id);
    }
}
