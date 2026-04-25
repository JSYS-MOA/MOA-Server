package com.moa.server.entity.user.service;

import com.moa.server.common.exception.CustomException;
import com.moa.server.common.exception.ErrorCode;
import com.moa.server.entity.user.*;
import com.moa.server.entity.user.dto.MyInfoResponseDTO;
import com.moa.server.entity.user.dto.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
@RequiredArgsConstructor
public class MyInfoService {

    private final UserRepository userRepository;

    private SessionUser getLoginUser(HttpSession session) {
        SessionUser loginUser = (SessionUser) session.getAttribute(SessionUser.USER);
        if (loginUser == null) throw new CustomException(ErrorCode.UNAUTHORIZED);
        return loginUser;
    }

    public MyInfoResponseDTO getMyInfo(HttpSession session){
        SessionUser loginUser = getLoginUser(session);

        UserEntity user = userRepository.findById(loginUser.getUserId())
                .orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));

        //주민등록번호
        String residentNumber = "";
        if(user.getBirth() != null){
            String birth = user.getBirth().format(DateTimeFormatter.ofPattern("yyMMdd"));
            residentNumber = birth + "-*******";
        }

        //나이
        int age = 0;
        if( user.getBirth() != null){
            age = LocalDate.now().getYear() - user.getBirth().getYear() +1;
        }

        return MyInfoResponseDTO.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .employeeId(user.getEmployeeId())
                .departmentName(user.getDepartment().getDepartmentName())
                .gradeName(user.getGrade().getGradeName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .address(user.getAddress())
                .bank(user.getBank())
                .accountNum(user.getAccountNum())
                .startDate(user.getStartDate() != null ?
                        user.getStartDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) : "")
                .birth(user.getBirth() != null ?
                        user.getBirth().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) : "")
                .residentNumber(residentNumber)
                .age(age)
                .build();
    }
}
