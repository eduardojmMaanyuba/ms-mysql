package com.maanyuba.msmysql.repository;

import com.maanyuba.msmysql.entity.AccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountInfoRepository extends JpaRepository<AccountInfo, Integer> {
}
