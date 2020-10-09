package com.moBack.springboot.moBack.rest;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

	@GetMapping("/")
	public String sayHello() {
		return "Hello world! Time on server is " + LocalDateTime.now();
	}
	

}
