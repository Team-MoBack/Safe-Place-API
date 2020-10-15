package com.moBack.backend.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moBack.backend.util.Home;

@RestController
public class HomeController {
	//@Autowired HomeMapper homeMapper;
	
	@RequestMapping("/")
	public String home() {
		return "Hello World!";
	}
	
//	@RequestMapping("/{name}")
//	public Home home(@PathVariable String name) {
//		Home home = homeMapper.readHome(name);          
//		return home;
//	}
	
	@RequestMapping("/{name}/{message}")
	public Home home(
			@PathVariable String name,
			@PathVariable String message) {
		Home home = new Home();
		home.setName(name);
		home.setMessage(message);

		return home;
	}
	
    @RequestMapping("/admin")
    public String admin() {
         return "This is admin page";
    }
   
    @RequestMapping("/user")
    public String user() {
         return "this is user page";
    }
}