package com.moa.server.entity.base.controller;

import com.moa.server.entity.approval.ApprovalLineEntity;
import com.moa.server.entity.base.service.AllowanceService;
import com.moa.server.entity.base.service.ApprovalLineService;
import com.moa.server.entity.salary.AllowanceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/base/approval")
@RequiredArgsConstructor
public class ApprovalLineController {
    private final ApprovalLineService service;

    @GetMapping
    public List<ApprovalLineEntity> list() {
        return service.getList();
    }
    @GetMapping("/{approval_line_id}")
    public ApprovalLineEntity detail(@PathVariable Integer id) {
        return service.getDetail(id);
    }
    @PostMapping
    public void add(@RequestBody ApprovalLineEntity data) {
        service.register(data);
    }
    @PutMapping("/{approval_line_id}")
    public void update(@RequestBody ApprovalLineEntity data) {
        service.modify(data);
    }
    @DeleteMapping("/{approval_line_id}")
    public void delete(@PathVariable Integer id) {
        service.remove(id);
    }
}
