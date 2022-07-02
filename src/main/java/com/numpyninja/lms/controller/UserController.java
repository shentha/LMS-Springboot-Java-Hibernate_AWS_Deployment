package com.numpyninja.lms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.numpyninja.lms.dto.UserAndRoleDTO;
import com.numpyninja.lms.dto.UserDto;
import com.numpyninja.lms.entity.User;
import com.numpyninja.lms.exception.DuplicateResourceFound;
import com.numpyninja.lms.exception.InvalidDataException;
import com.numpyninja.lms.exception.ResourceNotFoundException;
import com.numpyninja.lms.mappers.UserMapper;
import com.numpyninja.lms.services.UserServices;

@RestController
//@RequestMapping("/users")
public class UserController {	
	
	private UserMapper userMapper;
	private UserServices userServices;
	
	@Autowired
    public UserController(
            UserMapper userMapper,
            UserServices userServices
      
    ) {
        this.userMapper = userMapper;
        this.userServices = userServices;
 
    }


	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> userList = userServices.getAllUsers();
		return ResponseEntity.ok(userList);  
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<UserDto> getAllUsersById(@PathVariable String id) throws ResourceNotFoundException {
		UserDto userDto = userServices.getAllUsersById(id);
		return ResponseEntity.status(200).body(userDto);
	}
	
    @GetMapping("/users/roles/{rolename}")
    protected List<?> getAllRoles(@PathVariable(value="rolename")String roleName) {
    	return userMapper.userDtos( userServices.getAllUsersByRole(roleName) );
    }
    
    @GetMapping("/users/programs/{programid}")
    protected List<?> getUsersForProgram(@PathVariable(value="programid")Long programId) {
        return userServices.getUsersForProgram(programId);
    }
    
    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto newuserDto) throws InvalidDataException, DuplicateResourceFound {
    	UserDto responseDto = userServices.createUser(newuserDto);
    	return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);  
    }
    
    @PostMapping("/users/roleStatus")
    public ResponseEntity<UserDto> createUserWithRole(@RequestBody UserAndRoleDTO newUserRoleDto) throws InvalidDataException, DuplicateResourceFound {
    	UserDto responseDto = userServices.createUserWithRole(newUserRoleDto);
    	return ResponseEntity.status(HttpStatus.CREATED).body(responseDto); 
    }
    
    @PutMapping("/users/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto updateuserDto, @PathVariable(value="userId") String userId) throws DuplicateResourceFound, ResourceNotFoundException, InvalidDataException {
    	UserDto responseDto = userServices.updateUser(updateuserDto, userId);
    	return ResponseEntity.status(HttpStatus.OK).body(responseDto); 
    }
    
   /* @PutMapping("/users/roleStatus/{userId}")
    public ResponseEntity<UserDto> updateUserWithRole(@RequestBody UserAndRoleDTO updateUserRoleDto, @PathVariable(value="userId") String userId) throws DuplicateResourceFound, ResourceNotFoundException, InvalidDataException {
    	UserDto responseDto = userServices.updateUserWithRole(updateUserRoleDto, userId);
    	return ResponseEntity.status(HttpStatus.OK).body(responseDto); 
    }*/
    
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable(value="userId") String userId) throws ResourceNotFoundException{
    	String deletedUserId = userServices.deleteUser(userId);
    	return ResponseEntity.status(HttpStatus.OK).body("Deleted User ID:  "+deletedUserId);
    	//return deletedUserId;
    }
    
   /* @PutMapping("/users/roleStatus/{userId}")
    public UserDto updateUserstatus(@RequestBody UserDto updateuserDto, @PathVariable(value="userId") String userId) throws DuplicateResourceFound, ResourceNotFoundException, InvalidDataException {
    	//UserDto responseDto = userServices.updateUserStatus(updateuserDto, userId);
    	//return responseDto;
    	return null;
    }*/
    
}
