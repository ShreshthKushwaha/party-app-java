package com.party.hoster.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.party.hoster.payloads.PartyDto;
import com.party.hoster.services.PartyService;

@RestController
@RequestMapping("/api/")
public class PartyController {
	
	@Autowired
	private PartyService partyService; 
	//create
	
	@PostMapping("/users/{userId}/parties")
	
	public ResponseEntity<PartyDto> createPost(
			@RequestBody PartyDto partyDto,
			@PathVariable Integer userId
			){
		PartyDto createdParty = this.partyService.createParty(partyDto, userId);
		return new ResponseEntity<PartyDto>(createdParty,HttpStatus.CREATED);
		
		
	}

}
