package com.party.hoster.controllers;

import java.util.List;

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

import com.party.hoster.payloads.ApiResponse;
import com.party.hoster.payloads.UserDto;
import com.party.hoster.services.UserService;

@RestController
@RequestMapping("/api/users")

public class UserController {
	
	@Autowired
	
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
		
		
	};
	
	//updating user
	
	 @PutMapping("/{id}")
	    public ResponseEntity<UserDto> updateUser(@PathVariable int id, @RequestBody UserDto updatedUserDto) {
	        UserDto updatedDto = this.userService.updateUser(updatedUserDto, id);
	        if (updatedDto != null) {
	            return new ResponseEntity<>(updatedDto, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	 
	// Delete user by ID
	    @DeleteMapping("/{id}")
	    public ResponseEntity<ApiResponse> deleteUser(@PathVariable int id) {
	        this.userService.deleteUser(id);
	        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully!",true),HttpStatus.OK);
	        
	        
	        
	    }
	    
	//get all users
	@GetMapping("/")    
	public ResponseEntity<List<UserDto>> getAllusers(){
		return ResponseEntity.ok(this.userService.getAllusers());
	}
	
	// Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable int id) {
        UserDto userDto = this.userService.getUserById(id);
        if (userDto != null) {
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	    
	    
	 
	 
	
	
	
	
	
	
	
	

}
