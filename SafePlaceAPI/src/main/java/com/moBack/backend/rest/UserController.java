package com.moBack.backend.rest;

import com.moBack.backend.dto.AccountDTO;
import com.moBack.backend.dto.LoginResultDTO;
import com.moBack.backend.dto.UserPwDTO;
import com.moBack.backend.service.TokenService;
import com.moBack.backend.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.moBack.backend.entity.Account;
import com.moBack.backend.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private UserService userService;
	private TokenService tokenService;

	@Autowired
	public UserController(UserService userService, TokenService tokenService) {
		this.userService = userService;
		this.tokenService = tokenService;
	}

	@ApiOperation(value = "유저를 등록합니다 ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 400, message = "잘못된 접근"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
	@PostMapping("/register")
    public String saveUser(@RequestBody AccountDTO account) {
        return userService.registerUser(account);
    }

    @PostMapping("/login")
	public LoginResultDTO login(@RequestBody UserPwDTO userPwDTO, HttpServletResponse response) {
		LoginResultDTO loginResultDTO = userService.login(userPwDTO);
		if (loginResultDTO.isSuccessful()) {
			String token = tokenService.saveToken(userPwDTO.getEmail());
			loginResultDTO.setToken(token);
		}
		else {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
		return loginResultDTO;
	}

	@PostMapping("/logout")
	public String logout(@RequestParam String token, HttpServletResponse response) {
		Optional<String> email = TokenUtil.getEmailFromToken(token);
		if (email.isPresent()) {
			tokenService.deleteToken(email.get());
			return "success";
		}
		return "fail";
	}

	@ApiOperation(value = "유저 id에 해당하는 유저정보를 받아옵니다 ")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "유저 id", required = true, dataType = "int", paramType = "path")
	})
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 400, message = "잘못된 접근"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
	@GetMapping("/{id}")
	public Account findById(@PathVariable int id) {
		Optional<Account> account = userService.findById(id);
		if (account.isPresent()) return account.get();
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user id not found - " + id);
	}

}
