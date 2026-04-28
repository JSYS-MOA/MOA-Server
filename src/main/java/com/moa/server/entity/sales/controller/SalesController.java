package com.moa.server.entity.sales.controller;
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
    private final SalesService transactionService;

    //전체조회
    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> getTransactions() {
        return ResponseEntity.ok(transactionService.getTransactions());
    }

    //상세조회
    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionResponseDTO> getTransaction(
            @PathVariable Integer transactionId) {
        return ResponseEntity.ok(transactionService.getTransaction(transactionId));
    }

    //수정
    @PutMapping("/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(
            @PathVariable Integer transactionId, @RequestBody TransactionRequestDTO request){
        transactionService.updateTransaction(transactionId, request);
        return ResponseEntity.ok().build();
    }

    //삭제
    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(
            @PathVariable Integer transactionId) {
        transactionService.deleteTransaction(transactionId);
        return ResponseEntity.ok().build();
    }
}
