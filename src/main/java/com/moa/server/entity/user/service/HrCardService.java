package com.moa.server.entity.user.service;

import com.moa.server.entity.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Service
@Transactional
@RequiredArgsConstructor
public class HrCardService {
    private final UserRepository userRepository;

    private final AdminRoleRepository adminRoleRepository;
    private final DepartmentRepository departmentRepository;
    private final GradeRepository gradeRepository;

    public boolean login(String employeeId, String password) {
        return userRepository.existsByEmployeeIdAndPassword(employeeId, password);
    }

    public UserEntity loginInfo(String employeeId) {
        return userRepository.getUserByEmployeeId(employeeId);
    }

    public List<UserEntity> hrCardList() {
        return userRepository.findAll();
    }

    public List<UserEntity> hrCardSearch(String searchCondition, String searchKeyword) {
        if (searchCondition == null || searchCondition.isBlank()) {
            return userRepository.findAll();
        }

        String condition = searchCondition.trim().toLowerCase(Locale.ROOT);
        String keyword = searchKeyword == null ? "" : searchKeyword.trim();

        return switch (condition) {
            case "username", "user_name", "name" -> userRepository.findByUserNameContaining(keyword);
            case "employeeid", "employee_id" -> userRepository.findByEmployeeIdContaining(keyword);
            case "email" -> userRepository.findByEmailContaining(keyword);
            case "departmentid", "department_id" -> userRepository.findByDepartmentId(parseInteger(keyword));
            case "gradeid", "grade_id" -> userRepository.findByGradeId(parseInteger(keyword));
            case "bank" -> userRepository.findByBank(keyword);
            case "active" -> userRepository.findByQuitDateIsNull();
            case "retired", "quit" -> userRepository.findByQuitDateIsNotNull();
            default -> throw new IllegalArgumentException("지원하지 않는 검색 조건입니다: " + searchCondition);
        };
    }

    public UserEntity hrCardInfo(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public UserEntity hrCardAdd(UserEntity user) {
        if (user.getStartDate() == null) {
            user.setStartDate(LocalDate.now());
        }

        return userRepository.save(user);
    }

    public UserEntity hrCardUpdate(Integer userId, UserEntity newUser) {
        UserEntity user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            user.setUserName(newUser.getUserName());
            user.setEmployeeId(newUser.getEmployeeId());
            user.setPassword(newUser.getPassword());
            user.setRoleId(newUser.getRoleId());
            user.setPhone(newUser.getPhone());
            user.setEmail(newUser.getEmail());
            user.setAddress(newUser.getAddress());
            user.setStartDate(newUser.getStartDate());
            user.setQuitDate(newUser.getQuitDate());
            user.setDepartmentId(newUser.getDepartmentId());
            user.setGradeId(newUser.getGradeId());
            user.setBirth(newUser.getBirth());
            user.setPerformance(newUser.getPerformance());
            user.setProfileUrl(newUser.getProfileUrl());
            user.setBank(newUser.getBank());
            user.setAccountNum(newUser.getAccountNum());

            return userRepository.save(user);
        }

        return null;
    }

    public void hrCardDelete(Integer userId) {
        userRepository.deleteById(userId);
    }

    private Integer parseInteger(String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 검색 조건에는 숫자 값을 입력해야 합니다.");
        }
    }
}
