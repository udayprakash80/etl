package com.saras.etl.repository;

import com.saras.etl.entity.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<Userinfo, Long> {
    Optional<Userinfo> findByName(String username);
}
