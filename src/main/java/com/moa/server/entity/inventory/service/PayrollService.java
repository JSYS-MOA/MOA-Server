package com.moa.server.entity.inventory.service;

import com.moa.server.entity.inventory.TransactionEntity;
import com.moa.server.entity.inventory.TransactionRepository;
import com.moa.server.entity.inventory.dto.TransactionSalaryDTO;
import com.moa.server.entity.inventory.dto.TransactionSalaryRequestDTO;
import com.moa.server.entity.salary.SalaryLedgerRepository;
import com.moa.server.entity.salary.SalaryRepository;
import com.moa.server.entity.user.AdminRoleRepository;
import com.moa.server.entity.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;

@Service
@Transactional
@RequiredArgsConstructor
public class PayrollService {
    private final UserRepository userRepository;
    private final AdminRoleRepository adminRoleRepository;
    private final SalaryRepository salaryRepository;
    private final SalaryLedgerRepository salaryLedgerRepository;
    private final TransactionRepository transactionRepository;

    public List<TransactionEntity> transactionSalaryList() {
        return transactionRepository.findAll();
    }

    public List<TransactionSalaryDTO> transactionSalaryResponseList() {
        return transactionRepository.findAll().stream()
                .map(this::toSalaryResponse)
                .toList();
    }

    public Page<TransactionSalaryDTO> transactionSalaryPageList(int page, int size) {
        return transactionRepository.findAll(createPageable(page, size))
                .map(this::toSalaryResponse);
    }

    public List<TransactionEntity> transactionSalarySearch(String searchCondition, String searchKeyword) {
        if (searchCondition == null || searchCondition.isBlank()) {
            return transactionRepository.findAll();
        }

        String condition = searchCondition.trim().toLowerCase(Locale.ROOT);
        String keyword = searchKeyword == null ? "" : searchKeyword.trim();




        return switch (condition) {
            case "salaryledgerid", "salary_ledger_id" -> transactionRepository.findBySalaryLedgerId(parseInteger(keyword));
            case "vendorid", "vendor_id" -> transactionRepository.findByVendorId(parseInteger(keyword));
            case "createdat", "created_at" -> {
                LocalDate date = parseLocalDate(keyword);
                yield transactionRepository.findByCreatedAtBetween(
                        date.atStartOfDay(),
                        date.plusDays(1).atStartOfDay()
                );
            }
            case "updatedat", "updated_at", "updateat" -> {
                LocalDate date = parseLocalDate(keyword);
                yield transactionRepository.findByUpdatedAtBetween(
                        date.atStartOfDay(),
                        date.plusDays(1).atStartOfDay()
                );
            }
            default -> throw new IllegalArgumentException("지원하지 않는 검색 조건입니다. " + searchCondition);
        };
    }

    public TransactionSalaryDTO transactionSalaryInfo(Integer transactionId) {
        return transactionRepository.findById(transactionId)
                .map(this::toSalaryResponse)
                .orElse(null);
    }

    public TransactionEntity transactionSalaryAdd(TransactionSalaryRequestDTO transactionRequest) {
        TransactionEntity transaction = TransactionEntity.builder()
                .transactionId(transactionRequest.getTransactionId())
                .vendorId(transactionRequest.getVendorId())
                .salaryLedgerId(transactionRequest.getSalaryLedgerId())
                .transactionNum(transactionRequest.getTransactionNum())
                .transactionType(transactionRequest.getTransactionType())
                .transactionPrice(transactionRequest.getTransactionPrice())
                .transactionMemo(transactionRequest.getTransactionMemo())
                .createdAt(transactionRequest.getCreatedAt())
                .updatedAt(transactionRequest.getUpdatedAt())
                .build();

        if (transaction.getCreatedAt() == null) {
            transaction.setCreatedAt(LocalDateTime.now());
        }

        return transactionRepository.save(transaction);
    }

    private Integer parseInteger(String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 검색 조건에는 숫자 값을 입력해야 합니다.");
        }
    }

    private LocalDate parseLocalDate(String value) {
        try {
            return LocalDate.parse(value);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("날짜 검색 조건에는 yyyy-MM-dd 형식의 값을 입력해야 합니다.");
        }
    }

    private Pageable createPageable(int page, int size) {
        int safePage = Math.max(page, 0);
        int safeSize = size > 0 ? size : 10;
        return PageRequest.of(safePage, safeSize, Sort.by(Sort.Direction.ASC, "transactionId"));
    }

    private TransactionSalaryDTO toSalaryResponse(TransactionEntity transaction) {
        return TransactionSalaryDTO.builder()
                .transactionId(transaction.getTransactionId())
                .vendorId(transaction.getVendorId())
                .salaryLedgerId(transaction.getSalaryLedgerId())
                .transactionNum(transaction.getTransactionNum())
                .transactionType(transaction.getTransactionType())
                .transactionPrice(transaction.getTransactionPrice())
                .transactionMemo(transaction.getTransactionMemo())
                .createdAt(transaction.getCreatedAt())
                .updatedAt(transaction.getUpdatedAt())
                .build();
    }
}
