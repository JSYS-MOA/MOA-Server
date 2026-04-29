package com.moa.server.entity.sales.service;

import com.moa.server.common.exception.CustomException;
import com.moa.server.common.exception.ErrorCode;
import com.moa.server.entity.inventory.*;
import com.moa.server.entity.inventory.dto.OrderDTO;
import com.moa.server.entity.sales.dto.TaxInvoiceResponseDTO;
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
    private final OrdererRepository ordererRepository;
    private final VendorRepository vendorRepository;

    private static final Integer SUPPLIER_VENDOR_ID = 11;

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

    //전자세금계산서 조회
    public TaxInvoiceResponseDTO getTaxInvoice(Integer transactionId){

        TransactionEntity t = transactionRepository.findById(transactionId)
                .orElseThrow(()-> new CustomException(ErrorCode.TRANSACTION_NOT_FOUND));

        VendorEntity supplier = vendorRepository.findById(SUPPLIER_VENDOR_ID)
                .orElseThrow(()-> new CustomException(ErrorCode.TRANSACTION_NOT_FOUND));

        VendorEntity receiver = vendorRepository.findById(t.getVendorId())
                .orElseThrow(()-> new CustomException(ErrorCode.TRANSACTION_NOT_FOUND));

        List<OrderDTO> items = ordererRepository.findByOrderformId(t.getOrderformId())
                .stream()
                .map(OrdererEntity::toinfoDTO)
                .toList();

        int supplyPrice = t.getTransactionPrice();
        int tax = (int) (supplyPrice * 0.1);
        int totalPrice = supplyPrice + tax;

        return TaxInvoiceResponseDTO.builder()

                .supplierName(supplier.getVendorName())
                .supplierCode(supplier.getVendorCord())
                .receiverName(receiver.getVendorName())
                .receiverCode(receiver.getVendorCord())
                .transactionId(t.getTransactionId())
                .transactionDate(t.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .supplyPrice(supplyPrice)
                .tax(tax)
                .totalPrice(totalPrice)
                .items(items)
                .build();

    }

    // 전자세금계산서 목록 조회
    public List<TransactionResponseDTO> getTaxInvoiceList() {
        return transactionRepository.findAllByOrderByTransactionIdDesc()
                .stream()
                .filter(t -> t.getOrderformId() != null)
                .map(this::toDTO)
                .toList();
    }
}
