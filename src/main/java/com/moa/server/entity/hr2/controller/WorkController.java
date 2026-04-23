package com.moa.server.entity.hr2.controller;

import com.moa.server.entity.hr2.dto.WorkDTO;
import com.moa.server.entity.hr2.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hr/attendances")
@RequiredArgsConstructor
public class WorkController {
    private final WorkService service;

    @GetMapping
    public List<WorkDTO> list() {
        return service.getList();
    }
    @GetMapping("/{workId}")
    public WorkDTO detail(@PathVariable Integer workId) {
        return service.getDetail(workId);
    }
    @PostMapping
    public void add(@RequestBody WorkDTO data) {
        service.register(data);
    }
    @PutMapping("/{workId}")
    public void update(@PathVariable Integer workId, @RequestBody WorkDTO data) {
        data.setWorkId(workId);
        service.modify(data);
    }
    @DeleteMapping("/{workId}")
    public void delete(@PathVariable Integer workId) {
        service.remove(workId);
    }

}
