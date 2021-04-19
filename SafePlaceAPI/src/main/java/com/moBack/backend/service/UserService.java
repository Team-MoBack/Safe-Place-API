package com.moBack.backend.service;

import java.util.List;

import com.moBack.backend.dto.AccountDTO;
import com.moBack.backend.dto.LoginResult;
import com.moBack.backend.dto.UserPwDTO;
import com.moBack.backend.entity.Account;

import javax.servlet.http.HttpSession;

public interface UserService {

	public String registerUser(AccountDTO account);

	public LoginResult login(UserPwDTO userPwDTO);

	public Account findById(int id);

	public void deleteById(int id);

}
