package com.party.hoster.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.party.hoster.entities.Party;
import com.party.hoster.entities.User;
import com.party.hoster.payloads.PartyDto;
import com.party.hoster.repositories.PartyRepo;
import com.party.hoster.repositories.UserRepo;
import com.party.hoster.services.PartyService;
import com.party.hoster.exceptions.ResourceNotFoundException;



@Service
public class PartyServiceImpl implements PartyService {
	
	@Autowired
	private PartyRepo partyRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Autowired
	private UserRepo userRepo;
	
	
	

	@Override
	public PartyDto createParty(PartyDto partyDto, Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","user id",userId));
		// TODO Auto-generated method stub
		Party party = this.modelMapper.map(partyDto, Party.class );
		//we can set image also here
		party.setUser(user);
		party.setPostedDate(LocalDateTime.now());
		
		Party savedParty = this.partyRepo.save(party);
		
		return this.modelMapper.map(savedParty,PartyDto.class);
	}

	@Override
	public PartyDto updateParty(PartyDto partyDto, Integer partyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteParty(Integer partyId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Party> getAllParty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PartyDto getPartyById(Integer partyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Party> getPartiesByUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Party> searchParties(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
