package com.party.hoster.services;


import java.util.List;

import com.party.hoster.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer id);
	UserDto getUserById(Integer id);
	List<UserDto> getAllusers();
	
	
	void deleteUser(Integer userId);
	
	
		
	

}
