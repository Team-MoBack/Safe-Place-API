package com.moBack.springboot.moBack.rest;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moBack.springboot.moBack.Model.MoBackUser;



@RestController
@RequestMapping("/api")
public class RegistrationController {
	
	@Autowired
	private UserDetailsManager userDetailsManager;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@GetMapping("/test")
	public String test() {
		return "test!!";
	}
	
	@PostMapping("/register")
	public String register(
			@ModelAttribute("moBackUser") MoBackUser user, 
				BindingResult theBindingResult, 
				Model theModel) {
						
		String userName = user.getUserName();
		
		logger.info("Processing registration form for: " + userName);
		
		// form validation
		if (theBindingResult.hasErrors()) {
			logger.warning("User name/password can not be empty.");
			throw new RuntimeException("User name/password can not be empty.");
		}
		
		// check the database if user already exists
		boolean userExists = doesUserExist(userName);
		
		if (userExists) {
			logger.warning("User name already exists.");
			throw new RuntimeException("User name already exists.");
		}
		

		// encrypt the password
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        // prepend the encoding algorithm id
        encodedPassword = "{bcrypt}" + encodedPassword;
                 
		// give user default role of "employee"
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("NORMAL_USER");

        // create user object (from Spring Security framework)
        User tempUser = new org.springframework.security.core.userdetails.User(userName, encodedPassword, authorities);

        // save user in the database
		userDetailsManager.createUser(tempUser);
        logger.info("Successfully created user: " + userName);
        
        return "Registration Success!";		
	}
	
	private boolean doesUserExist(String userName) {
		
		logger.info("Checking if user exists: " + userName);
		
		// check the database if the user already exists
		boolean exists = userDetailsManager.userExists(userName);
		logger.info("User: " + userName + ", exists: " + exists);
		
		return exists;
	}

}

