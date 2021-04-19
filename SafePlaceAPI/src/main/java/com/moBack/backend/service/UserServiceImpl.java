package com.moBack.backend.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import com.moBack.backend.dto.AccountDTO;
import com.moBack.backend.dto.LoginResult;
import com.moBack.backend.dto.UserPwDTO;
import org.springframework.session.data.redis.RedisSessionRepository;
import org.springframework.stereotype.Service;

import com.moBack.backend.dao.AccountRepository;
import com.moBack.backend.entity.Account;

@Service
public class UserServiceImpl implements UserService {

	private AccountRepository accountRepository;

	public UserServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	@Transactional
	public String registerUser(AccountDTO account) {
		if (accountRepository.findByEmail(account.getEmail()).isPresent()) {
			return "fail";
		}
		accountRepository.save(Account.builder()
				.firstName(account.getFirstName())
				.lastName(account.getLastName())
				.email(account.getEmail())
				.password(account.getPassword())
				.build());
		return "success";
	}

	@Override
	@Transactional
	public LoginResult login(UserPwDTO userPwDTO) {
		Optional<Account> account = accountRepository.findByEmail(userPwDTO.getEmail());
		if (account.isPresent()) {
			if (userPwDTO.getPassword().equals(account.get().getPassword())) {
				return LoginResult.builder()
						.isSuccessful(true)
						.message("Login Success")
						.build();
			}
		}
		return LoginResult.builder()
				.isSuccessful(false)
				.message("Email or password is incorrect")
				.build();
	}

	@Override
	@Transactional
	public Account findById(int id) {
		Optional<Account> result = accountRepository.findById(id);
		Account account = null;
		if (result.isPresent()) {
			account = result.get();
		}
		else {
			throw new RuntimeException("Did not find user id - "+id);
		}
		return account;
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		accountRepository.deleteById(id);
	}
}
