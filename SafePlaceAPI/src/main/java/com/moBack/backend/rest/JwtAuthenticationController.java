package com.moBack.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.moBack.backend.dto.JwtRequest;
import com.moBack.backend.dto.JwtResponse;
import com.moBack.backend.entity.User;
import com.moBack.backend.jwt.JwtTokenUtil;
import com.moBack.backend.service.JwtUserDetailsService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailService;
    
	@ApiOperation(value = "로그인 ")
    @ApiImplicitParam(name = "JWT", required = false, paramType = "authorization")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
    @PostMapping("api/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        final User user = userDetailService.authenticateByEmailAndPassword
                (authenticationRequest.getEmail(), authenticationRequest.getPassword());
        final String token = jwtTokenUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(new JwtResponse(token));
    }

}

