package com.moa.server.entity.salary.service;

import com.moa.server.entity.salary.*;
import com.moa.server.entity.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//급여 회계 관련
@Service
@Transactional
@RequiredArgsConstructor
public class SalaryService {
    private final UserRepository userRepository;

    private final SalaryRepository salaryRepository;
    private final SalaryLedgerRepository salaryLedgerRepository;
    private final AllowanceRepository allowanceRepository;
    private final TransfeRepository transfeRepository;
    private final CompanyAccountRepository companyAccountRepository;


}
