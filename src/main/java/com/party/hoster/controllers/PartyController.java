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

import com.party.hoster.exceptions.ResourceNotFoundException;
import com.party.hoster.payloads.ApiResponse;
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
	//getting parties by user
	
	// Update an existing party
    @PutMapping("/parties/{partyId}")
    public ResponseEntity<PartyDto> updateParty(
            @RequestBody PartyDto partyDto,
            @PathVariable Integer partyId
    ) {
        PartyDto updatedParty = partyService.updateParty(partyDto, partyId);
        return new ResponseEntity<>(updatedParty, HttpStatus.OK);
    }
	
	@GetMapping("/users/{userId}/parties")
    public ResponseEntity<List<PartyDto>> getPartiesByUser(@PathVariable Integer userId) {
        List<PartyDto> parties = partyService.getPartiesByUser(userId);
        return new ResponseEntity<>(parties, HttpStatus.OK);
    }
	
	//getting party by its id
	 @GetMapping("/parties/{partyId}")
	    public ResponseEntity<PartyDto> getPartyById(@PathVariable Integer partyId) {
	        PartyDto partyDto = partyService.getPartyById(partyId);
	        return ResponseEntity.ok(partyDto);
	    }
	 
	 //getting all parties
	 @GetMapping("/parties")
	    public ResponseEntity<List<PartyDto>> getAllParties() {
	        List<PartyDto> partyDtos = partyService.getAllParty();
	        return ResponseEntity.ok(partyDtos);
	    }
	 @DeleteMapping("/parties/{partyId}")
	 public ResponseEntity<ApiResponse> deleteParty(@PathVariable Integer partyId) {
	     try {
	         // Retrieve the party to be "deleted" from the service
	         partyService.deleteParty(partyId);
	         
	        // if (deletedParty != null) {
	            return new ResponseEntity<>(new ApiResponse("Party marked as inactive successfully!", true), HttpStatus.OK);
	        // } else {
	          //   return new ResponseEntity<>(new ApiResponse("Party not found!", false), HttpStatus.NOT_FOUND);
	        // }
	     } catch (ResourceNotFoundException ex) {
	         return new ResponseEntity<>(new ApiResponse("INVALID PARTY ID!", false), HttpStatus.NOT_FOUND);
	     }
	 }

}
