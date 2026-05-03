package com.moa.server.entity.salary.controller;

import com.moa.server.entity.inventory.TransactionEntity;
import com.moa.server.entity.inventory.dto.TransactionSalaryDTO;
import com.moa.server.entity.inventory.dto.TransactionSalaryRequestDTO;
import com.moa.server.entity.inventory.service.PayrollService;
import com.moa.server.entity.salary.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/api/salary", "/api/hr/payroll"})
@RequiredArgsConstructor
public class SalaryController {

    private final PayrollService payrollService;

    @GetMapping({"", "/", "/payroll"})
    public List<TransactionSalaryDTO> getPayrolls() {
        return payrollService.transactionSalaryResponseList();
    }

    @GetMapping({"/page", "/payrolls/page"})
    public Page<TransactionSalaryDTO> getPayrollsPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return payrollService.transactionSalaryPageList(page, size);
    }

    @GetMapping({"/search", "/payrolls/search"})
    public List<TransactionSalaryDTO> searchPayrolls(
            @RequestParam(required = false) String searchCondition,
            @RequestParam(required = false) String searchKeyword
    ) {
        return payrollService.transactionSalaryResponseSearch(searchCondition, searchKeyword);
    }

    @GetMapping({"/search/page", "/payrolls/search/page"})
    public Page<TransactionSalaryDTO> searchPayrollsPage(
            @RequestParam(required = false) String searchCondition,
            @RequestParam(required = false) String searchKeyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return payrollService.transactionSalaryPageSearch(searchCondition, searchKeyword, page, size);
    }

    @GetMapping({"/{transactionId}", "/payrolls/{transactionId}"})
    public TransactionSalaryDTO getPayroll(@PathVariable Integer transactionId) {
        return payrollService.transactionSalaryInfo(transactionId);
    }

    @PostMapping({"", "/", "/add", "/payrolls/add"})
    public TransactionEntity addPayroll(@RequestBody TransactionSalaryRequestDTO transactionRequest) {
        return payrollService.transactionSalaryAdd(transactionRequest);
    }

    @PutMapping({"/{transactionId}", "/payrolls/{transactionId}"})
    public TransactionEntity updatePayroll(
            @PathVariable Integer transactionId,
            @RequestBody TransactionSalaryRequestDTO transactionRequest
    ) {
        return payrollService.transactionSalaryUpdate(transactionId, transactionRequest);
    }
}
