package com.moa.server.entity.base.controller;


import com.moa.server.entity.base.service.AllowanceService;
import com.moa.server.entity.base.service.DepartmentService;
import com.moa.server.entity.salary.AllowanceEntity;
import com.moa.server.entity.user.DepartmentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/base/dept")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService service;

    @GetMapping
    public List<DepartmentEntity> list() {
        return service.getList();
    }
    @GetMapping("/{departmentId}")
    public DepartmentEntity detail(@PathVariable Integer departmentId) {
        return service.getDetail(departmentId);
    }
    @PostMapping
    public void add(@RequestBody DepartmentEntity data) {
        service.register(data);
    }
    @PutMapping("/{departmentId}")
    public void update(@RequestBody DepartmentEntity data) {
        service.modify(data);
    }
    @DeleteMapping("/{departmentId}")
    public void delete(@PathVariable Integer departmentId) {
        service.remove(departmentId);
    }
}
