package com.jwtDemo.jwtDemo.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwtDemo.jwtDemo.member.Member;
import com.jwtDemo.jwtDemo.member.MemberRepository;

import lombok.Data;

@RestController
public class MemberController {

    final MemberRepository memberRepository;
    final PasswordEncoder encode;
    
    public MemberController(MemberRepository memberRepository, PasswordEncoder encode) {
		this.memberRepository = memberRepository;
		this.encode = encode;
	}

	@PostMapping("/api/member")
    public String saveMember(@RequestBody MemberDto memberDto) {
        memberRepository.save(Member.createMember(memberDto.getEmail(), encode.encode(memberDto.getPassword())));
        return "success";
    }

}

@Data
class MemberDto {
    private String email;
    private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
