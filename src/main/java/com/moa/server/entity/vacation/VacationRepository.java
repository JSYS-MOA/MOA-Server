package com.moa.server.entity.vacation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface VacationRepository extends JpaRepository<VacationEntity, Integer> {

    List<VacationEntity> findByUserIdIn(Collection<Integer> userIds);
}