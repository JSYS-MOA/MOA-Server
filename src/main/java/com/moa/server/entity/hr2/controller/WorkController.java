package com.moa.server.entity.hr2.controller;

import com.moa.server.entity.hr2.dto.FilterDTO;
import com.moa.server.entity.hr2.dto.SelectMappingDTO;
import com.moa.server.entity.hr2.dto.WorkDTO;
import com.moa.server.entity.hr2.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/hr/attendances")
@RequiredArgsConstructor
public class WorkController {
    private final WorkService service;

    @GetMapping
    public Page<WorkDTO> list(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "15") int size,
                              FilterDTO filterDTO) {
        return service.getList(page,size, filterDTO);
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
    // 프론트에서 "사원번호 입력 시" 호출하는 엔드포인트
    @GetMapping("/{employeeId}")
    public ResponseEntity<SelectMappingDTO> fetchEmployee(@PathVariable String employeeId) {
        return ResponseEntity.ok(service.getEmployeeDetails(employeeId));
    }

    // 프론트에서 "수당코드 입력 시" 호출하는 엔드포인트
    @GetMapping("/{allowanceCord}")
    public ResponseEntity<SelectMappingDTO> fetchAllowance(@PathVariable String allowanceCord) {
        return ResponseEntity.ok(service.getAllowanceDetails(allowanceCord));
    }
}


