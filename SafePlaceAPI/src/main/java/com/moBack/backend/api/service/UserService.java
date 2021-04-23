package com.moBack.backend.api.service;

import com.moBack.backend.api.dto.AccountDTO;
import com.moBack.backend.api.dto.LoginResultDTO;
import com.moBack.backend.api.dto.UserPwDTO;
import com.moBack.backend.api.entity.Account;

import java.util.Optional;

public interface UserService {

	public String registerUser(AccountDTO account);

	public LoginResultDTO login(UserPwDTO userPwDTO);

	public Optional<Account> findById(int id);

	public void deleteById(int id);

}
