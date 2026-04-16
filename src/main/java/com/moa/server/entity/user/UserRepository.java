package com.moa.server.entity.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends  JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmployeeIdAndPassword(String employeeId, String password);

}
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    boolean existsByEmployeeIdAndPassword(String employeeId, String password);

    UserEntity getUserByEmployeeId(String employeeId);

    UserEntity getUserByUserId(Integer userId);

    List<UserEntity> findByUserNameContaining(String userName);

    List<UserEntity> findByEmployeeIdContaining(String employeeId);

    List<UserEntity> findByStartDateBetween(LocalDate start, LocalDate end);

    List<UserEntity> findByQuitDateBetween(LocalDate start, LocalDate end);

    List<UserEntity> findByQuitDateIsNotNull();

    List<UserEntity> findByQuitDateIsNull();

    UserEntity findByEmail(String email);

    List<UserEntity> findByEmailContaining(String email);

    List<UserEntity> findByDepartmentId(Integer departmentId);

    List<UserEntity> findByGradeId(Integer gradeId);

    List<UserEntity> findByBank(String bank);
}
