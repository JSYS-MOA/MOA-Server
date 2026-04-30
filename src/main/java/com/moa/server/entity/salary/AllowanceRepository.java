package com.moa.server.entity.salary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AllowanceRepository extends JpaRepository<AllowanceEntity, Integer> {
    Optional<AllowanceEntity> findByAllowanceName(String allowanceName);

    Optional<AllowanceEntity> findByAllowanceCord(String allowanceCode);
    //예시
    //List<BoardVOEntity> findByTitleContaining  (String title);

}

