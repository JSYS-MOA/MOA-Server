package com.moa.server.entity.user;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    boolean existsByEmployeeIdAndPassword(String employeeId, String password);
    UserEntity getUserByEmployeeId(String employeeId);

    UserEntity getUserByUserId(Integer userId);

    //검색용

    // 이름에 특정 글자가 포함된 유저 검색
    List<UserEntity> findByUserNameContaining(String userName);

    // 사번에 특정 글자가 포함된 유저 검색
    List<UserEntity> findByEmployeeIdContaining(String employeeId);

    // 특정 기간 입사자 검색
    List<UserEntity> findByStartDateBetween(LocalDate start, LocalDate end);

    // 퇴사일이 정확히 같은 유저 검색
    List<UserEntity> findByQuitDateBetween(LocalDate start, LocalDate end);

    // 퇴사한 사람들
    List<UserEntity> findByQuitDateIsNotNull();

    // 재직 중인 사람들
    List<UserEntity> findByQuitDateIsNull();

    // 이메일로 유저 찾기
    UserEntity findByEmail(String email);

    // 부서별 유저 목록
    List<UserEntity> findByDepartmentId(Integer departmentId);

    // 직급별 유저 목록
    List<UserEntity> findByGradeId(Integer gradeId);

    // 은행 별 유정 목록
    List<UserEntity> findByBank(String bank);


}

