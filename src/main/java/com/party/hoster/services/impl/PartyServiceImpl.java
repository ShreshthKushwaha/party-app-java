package com.party.hoster.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
		Party existingParty = partyRepo.findById(partyId)
                .orElseThrow(() -> new ResourceNotFoundException("Party", "partyId", partyId));

        // Update properties
        existingParty.setTitle(partyDto.getTitle());
        existingParty.setAddress(partyDto.getAddress());
        existingParty.setZip(partyDto.getZip());
        existingParty.setPartyDate(partyDto.getPartyDate());
        existingParty.setStartTime(partyDto.getStartTime());
        existingParty.setEndTime(partyDto.getEndTime());
        existingParty.setDescription(partyDto.getDescription());

        // Save the updated party
        Party updatedParty = partyRepo.save(existingParty);

        return modelMapper.map(updatedParty, PartyDto.class);
	}

	@Override
	public void deleteParty(Integer partyId) {
		// TODO Auto-generated method stub
		Party party = partyRepo.findById(partyId)
                .orElseThrow(() -> new ResourceNotFoundException("Party", "partyId", partyId));
		partyRepo.delete(party);

	}

	@Override
	public List<PartyDto> getAllParty() {
		List<Party> parties = partyRepo.findAll();

        // Convert Party entities to PartyDto using ModelMapper
        return parties.stream()
                .map(party -> modelMapper.map(party, PartyDto.class))
                .collect(Collectors.toList());
		// TODO Auto-generated method stub
		
	}

	@Override
	public PartyDto getPartyById(Integer partyId) {
		// TODO Auto-generated method stub
		Party party = partyRepo.findById(partyId)
                .orElseThrow(() -> new ResourceNotFoundException("Party", "partyId", partyId));

        // Convert Party entity to PartyDto using ModelMapper
        return modelMapper.map(party, PartyDto.class);
	}

	@Override
	public List<PartyDto> getPartiesByUser(Integer userId) {
		// Retrieve the user from the repository
	    User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));

	    // Retrieve parties associated with the user
	    List<Party> parties = partyRepo.findByUser(user);

	    // Convert Party entities to PartyDto objects
	    List<PartyDto> partyDtos = parties.stream()
	            .map(party -> modelMapper.map(party, PartyDto.class))
	            .collect(Collectors.toList());

	    return partyDtos;
	}

	@Override
	public List<Party> searchParties(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
