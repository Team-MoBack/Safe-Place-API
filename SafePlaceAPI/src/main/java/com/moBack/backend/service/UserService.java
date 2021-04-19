package com.moBack.backend.service;

import com.moBack.backend.dto.AccountDTO;
import com.moBack.backend.dto.LoginResultDTO;
import com.moBack.backend.dto.UserPwDTO;
import com.moBack.backend.entity.Account;

import java.util.Optional;

public interface UserService {

	public String registerUser(AccountDTO account);

	public LoginResultDTO login(UserPwDTO userPwDTO);

	public Optional<Account> findById(int id);

	public void deleteById(int id);

}
