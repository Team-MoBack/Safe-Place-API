package com.moBack.backend.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moBack.backend.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    public Optional<Account> findByEmail(String email);
}
