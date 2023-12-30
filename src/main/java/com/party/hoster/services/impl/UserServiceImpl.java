package com.party.hoster.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.party.hoster.entities.User;
import com.party.hoster.payloads.UserDto;
import com.party.hoster.repositories.UserRepo;
import com.party.hoster.services.UserService;
import com.party.hoster.exceptions.*;

@Service
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	private UserRepo userRepo;
	

	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = this.dtoToUser(userDto);
		//saving object in database
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
		
		// TODO Auto-generated method stub
		
		
	
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User"," Id ",userId));
		
		 // Update the fields of the existing user with data from the incoming UserDto
	    user.setFname(userDto.getFname());
	    user.setLname(userDto.getLname());
	    user.setAddress(userDto.getAddress());
	    user.setPhone(userDto.getPhone());
	    user.setMajor(userDto.getMajor());
	    user.setPassword(userDto.getPassword());
	    user.setEmail(userDto.getEmail());
	    user.setAbout(userDto.getAbout());
	    //user.setDateOfBirth(userDto.getDateOfBirth());
	    user.setZip(userDto.getZip());

	    // Save the updated user back to the database
	    User updatedUser = userRepo.save(user);

	    // Convert the updated user to a UserDto
	    UserDto userDto1 = userToDto(updatedUser);
	    return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User"," Id ",userId));
		
		
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllusers() {
		// TODO Auto-generated method stub
		
		List<User> users = this.userRepo.findAll();
		
		List<UserDto> userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User"," Id ",userId));
		this.userRepo.delete(user);
		

	}
	
	public User dtoToUser(UserDto userDto) {
		User user = new User();
		user.setId(userDto.getId());
        user.setFname(userDto.getFname());
        user.setLname(userDto.getLname());
        user.setAddress(userDto.getAddress());
        user.setPhone(userDto.getPhone());
        user.setMajor(userDto.getMajor());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setZip(userDto.getZip());

        return user;
		
	}
	
	public UserDto userToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFname(user.getFname());
        userDto.setLname(user.getLname());
        userDto.setAddress(user.getAddress());
        userDto.setPhone(user.getPhone());
        userDto.setMajor(user.getMajor());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        userDto.setAbout(user.getAbout());
        userDto.setDateOfBirth(user.getDateOfBirth());
        userDto.setZip(user.getZip());

        return userDto;
    }

}
