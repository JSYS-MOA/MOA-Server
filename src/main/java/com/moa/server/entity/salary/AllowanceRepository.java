package com.moa.server.entity.salary;

import com.moa.server.entity.user.AdminRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;
import java.util.Optional;

@Repository
public interface AllowanceRepository extends JpaRepository<AllowanceEntity, Integer> {
    Optional<AllowanceEntity> findByAllowanceName(String allowanceName);
    Optional<AllowanceEntity> findByAllowanceCord(String allowanceCord);
    //예시
    //List<BoardVOEntity> findByTitleContaining  (String title);

}

