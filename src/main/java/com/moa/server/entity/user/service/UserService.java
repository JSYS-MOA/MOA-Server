package com.moa.server.entity.user.service;

import com.moa.server.entity.user.*;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

// 유저 인사관련
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final AdminRoleRepository adminRoleRepository;
    private final DepartmentRepository departmentRepository;
    private final GradeRepository gradeRepository;

    public boolean login(String employeeId, String password) {
        return userRepository.existsByEmployeeIdAndPassword( employeeId , password );
    }

    public UserEntity loginInfo(String employeeId) {
        return userRepository.getUserByEmployeeId(employeeId);
    }

    //인사 카드 전체 조회
    public List<UserEntity> hrCardList() {return userRepository.findAll();}

    //인사 카드 상세 조회
    public UserEntity hrCardInfo(Integer userId) {return userRepository.findById(userId).orElse(null);}

    //인사 카드 추가
    public UserEntity hrCardAdd(UserEntity user){

        //기본 값 선택
        if(user.getStartDate() == null){
            user.setStartDate(LocalDate.now());
        }

        return userRepository.save(user);
    }


   //인사 카드 수정
    public UserEntity hrCardUpdate(Integer userId , UserEntity newUser){
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

   //인사 카드 삭제
   public void hrCardDelte(Integer userId) {userRepository.deleteById(userId);}


}
