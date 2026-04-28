package com.moa.server.entity.sales.service;

import com.moa.server.common.exception.CustomException;
import com.moa.server.common.exception.ErrorCode;
import com.moa.server.entity.inventory.TransactionEntity;
import com.moa.server.entity.inventory.TransactionRepository;
import com.moa.server.entity.sales.dto.TransactionRequestDTO;
import com.moa.server.entity.sales.dto.TransactionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SalesService {

    private final TransactionRepository transactionRepository;

    private TransactionResponseDTO toDTO(TransactionEntity t){
        return TransactionResponseDTO.builder()
                .transactionId(t.getTransactionId())
                .vendorId(t.getVendorId())
                .vendorCode(t.getVendor().getVendorCord())
                .vendorName(t.getVendor().getVendorName())
                .orderformId(t.getOrderformId())
                .salaryLedgerId(t.getSalaryLedgerId())
                .transactionNum(t.getTransactionNum())
                .transactionType(t.getTransactionType())
                .transactionPrice(t.getTransactionPrice())
                .transactionMemo(t.getTransactionMemo())
                .createdAt(t.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }

    //전체조회
    public List<TransactionResponseDTO> getTransactions() {
        return transactionRepository.findAllByOrderByTransactionIdDesc()
                .stream().map(this::toDTO).toList();
    }

    //상세조회
    public TransactionResponseDTO getTransaction(Integer transactionId){
        TransactionEntity t = transactionRepository.findById(transactionId)
                .orElseThrow(()-> new CustomException(ErrorCode.TRANSACTION_NOT_FOUND));
        return toDTO(t);
    }

    //수정
    public void updateTransaction(Integer transactionId, TransactionRequestDTO request){
        TransactionEntity t = transactionRepository.findById(transactionId)
                .orElseThrow(()-> new CustomException(ErrorCode.TRANSACTION_NOT_FOUND));
        t.setTransactionPrice(request.getTransactionPrice());
        t.setTransactionMemo(request.getTransactionMemo());
    }

    //삭제
    public void deleteTransaction(Integer transactionId){
        TransactionEntity t = transactionRepository.findById(transactionId)
                .orElseThrow(()-> new CustomException(ErrorCode.TRANSACTION_NOT_FOUND));

        transactionRepository.delete(t);
    }
}
