package com.moa.server.entity.salary;

import com.moa.server.entity.user.AdminRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyAccountRepository extends JpaRepository<CompanyAccountEntity, Integer> {

    //예시
    //List<BoardVOEntity> findByTitleContaining  (String title);

}

