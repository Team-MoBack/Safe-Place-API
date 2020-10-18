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
import io.swagger.annotations.ApiImplicitParams;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailService;
    
    @ApiImplicitParam(name = "JWT", required = false, paramType = "authorization")
    @PostMapping("api/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        final User user = userDetailService.authenticateByEmailAndPassword
                (authenticationRequest.getEmail(), authenticationRequest.getPassword());
        final String token = jwtTokenUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(new JwtResponse(token));
    }

}

