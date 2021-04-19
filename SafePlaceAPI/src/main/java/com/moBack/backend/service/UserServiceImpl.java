package com.moBack.backend.service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.moBack.backend.dto.AccountDTO;
import com.moBack.backend.dto.LoginResultDTO;
import com.moBack.backend.dto.UserPwDTO;
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
	public LoginResultDTO login(UserPwDTO userPwDTO) {
		Optional<Account> account = accountRepository.findByEmail(userPwDTO.getEmail());
		if (account.isPresent()) {
			if (userPwDTO.getPassword().equals(account.get().getPassword())) {
				return LoginResultDTO.builder()
						.isSuccessful(true)
						.message("Login Success")
						.build();
			}
		}
		return LoginResultDTO.builder()
				.isSuccessful(false)
				.message("Email or password is incorrect")
				.build();
	}

	@Override
	@Transactional
	public Optional<Account> findById(int id) {
		return accountRepository.findById(id);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		accountRepository.deleteById(id);
	}
}
