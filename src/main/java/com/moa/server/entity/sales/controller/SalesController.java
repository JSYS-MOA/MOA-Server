package com.moa.server.entity.sales.controller;
import com.moa.server.entity.sales.dto.TaxInvoiceResponseDTO;
import com.moa.server.entity.sales.dto.TransactionRequestDTO;
import com.moa.server.entity.sales.dto.TransactionResponseDTO;
import com.moa.server.entity.sales.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales/journals")
@RequiredArgsConstructor
public class SalesController {
    private final SalesService salesService;

    //전체조회
    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> getTransactions() {
        return ResponseEntity.ok(salesService.getTransactions());
    }

    //상세조회
    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionResponseDTO> getTransaction(
            @PathVariable Integer transactionId) {
        return ResponseEntity.ok(salesService.getTransaction(transactionId));
    }

    //수정
    @PutMapping("/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(
            @PathVariable Integer transactionId, @RequestBody TransactionRequestDTO request){
        salesService.updateTransaction(transactionId, request);
        return ResponseEntity.ok().build();
    }

    //삭제
    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(
            @PathVariable Integer transactionId) {
        salesService.deleteTransaction(transactionId);
        return ResponseEntity.ok().build();
    }

    //전자세금계산서 조회
    @GetMapping("/{transactionId}/tax-invoice")
    public ResponseEntity<TaxInvoiceResponseDTO> getTaxInvoice(@PathVariable Integer transactionId){
        return ResponseEntity.ok(salesService.getTaxInvoice(transactionId));
    }
}
