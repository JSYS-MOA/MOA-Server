package com.moa.server.entity.vacation.service;

import com.moa.server.entity.user.UserRepository;
import com.moa.server.entity.vacation.BasicVacationRepository;
import com.moa.server.entity.vacation.VacationRepository;
import com.moa.server.entity.vacation.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//근태 휴가
@Service
@Transactional
@RequiredArgsConstructor
public class VacationService {
    private final UserRepository userRepository;

    private final VacationRepository vacationRepository;
    private final BasicVacationRepository basicVacationRepository;
    private final WorkRepository workRepository;

}
