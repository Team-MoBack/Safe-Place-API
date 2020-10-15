package com.moBack.backend.util;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.moBack.backend.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
