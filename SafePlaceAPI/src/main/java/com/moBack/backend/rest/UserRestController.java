package com.moBack.backend.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.moBack.backend.entity.Position;
import com.moBack.backend.entity.User;
import com.moBack.backend.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	
	private UserService userService;
	
	@Autowired
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	@ApiOperation(value = "모든 유저정보를 받아옵니다 ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
	@GetMapping("")
	public List<User> findAll(){
		return userService.findAll();
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
	public User getUser(@PathVariable int id) {
		User user = userService.findById(id);
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user id not found - " + id);
		}
		return user;
	}
	
	@ApiOperation(value = "아이디 비밀번호를 넘기면 인증여부를 리턴합니다 ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 400, message = "잘못된 접근"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
	@PostMapping("/login")
	public User authenticate(@RequestBody User user){
		List<User> users = userService.findAll();
		String email = user.getEmail();
		String pw = user.getPassword();
		for (User temp : users) {
			if (email.equals(temp.getEmail()) && pw.equals(temp.getPassword())) {
				return user;
			}
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email or password is not matched");
	}
	
	@ApiOperation(value = "유저를 등록합니다 ")
	@PostMapping("/register")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 400, message = "잘못된 접근"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
	public User register(@RequestBody User user) {
		
		List<User> users = userService.findAll();
		for (User temp : users) {
			if (temp.getEmail().equals(user.getEmail())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user id is duplicated - " + user.getId());
			}
		}
		userService.save(user);
		return user;
	}
	
	@ApiOperation(value = "가게위치와 반경을 넘기면 가게주변 유저정보를 받아옵니다 ")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "radius", value = "반경", required = true, dataType = "double", paramType = "path")
	})
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
	@PostMapping("/search/{radius}")
	public List<User> findUserFromPosition(@PathVariable double radius, @RequestBody Position center){
		return userService.findUserFromPosition(center, radius);
	}
	
	@ApiOperation(value = "유저 id와 위치를 넘기면 유저의 위치를 갱신합니다 ")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "유저 id", required = true, dataType = "int", paramType = "path")
	})
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 400, message = "잘못된 접근"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
	@PutMapping("/position/{id}")
	public Position updatePosition(@PathVariable int id, @RequestBody Position pos) {
		User user = userService.findById(id);
		if (user == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"user id is not found");
		userService.updatePosition(id, pos);
		return pos;
	}
	
	
}
