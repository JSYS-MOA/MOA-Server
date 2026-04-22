package com.moa.server.entity.base.controller;

import com.moa.server.entity.approval.DocumentEntity;
import com.moa.server.entity.base.service.AllowanceService;
import com.moa.server.entity.base.service.DocumentService;
import com.moa.server.entity.salary.AllowanceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/base/form")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService service;

    @GetMapping
    public List<DocumentEntity> list() {
        return service.getList();
    }
    @GetMapping("/{document_id}")
    public DocumentEntity detail(@PathVariable Integer id) {
        return service.getDetail(id);
    }
    @PostMapping
    public void add(@RequestBody DocumentEntity data) {
        service.register(data);
    }
    @PutMapping("/{document_id}")
    public void update(@RequestBody DocumentEntity data) {
        service.modify(data);
    }
    @DeleteMapping("/{document_id}")
    public void delete(@PathVariable Integer id) {
        service.remove(id);
    }

}
