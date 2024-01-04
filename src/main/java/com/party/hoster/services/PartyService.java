package com.party.hoster.services;

import java.util.List;

import com.party.hoster.entities.Party;
import com.party.hoster.payloads.PartyDto;

public interface PartyService {
	
	//create
	
	PartyDto createParty(PartyDto partyDto, Integer userId);
	
	//update
	PartyDto updateParty(PartyDto partyDto,Integer partyId);
	
	//delete party
	void deleteParty(Integer partyId);
	
	//get all parties
	List<PartyDto> getAllParty();
	
	
	//get single party
	PartyDto getPartyById(Integer partyId);
	
	//get parties by user
	
	List<PartyDto> getPartiesByUser(Integer userId);
	
	//search parties
	List<Party> searchParties(String keyword);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
