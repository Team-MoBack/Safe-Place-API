package com.moBack.backend.api.dao;

import java.util.Optional;

import com.moBack.backend.api.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    public Optional<Account> findByEmail(String email);
}
