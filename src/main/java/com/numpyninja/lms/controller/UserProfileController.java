package com.numpyninja.lms.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;





@RestController
@RequestMapping
public class UserProfileController {
	
	//@GetMapping(value = "/userprofile")
	//private List<User> getPrograms()  
	{ 
		System.out.println("in get user profile");
		
		//return empRepo.findAll();  
	}  

}
