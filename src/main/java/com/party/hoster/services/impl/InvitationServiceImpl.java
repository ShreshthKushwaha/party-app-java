package com.party.hoster.services.impl;


import java.time.LocalDateTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.party.hoster.entities.Invitation;
import com.party.hoster.entities.Party;
import com.party.hoster.entities.User;
import com.party.hoster.enums.InvitationStatus;
import com.party.hoster.exceptions.ResourceNotFoundException;
import com.party.hoster.payloads.InvitationDto;
import com.party.hoster.repositories.InvitationRepo;
import com.party.hoster.repositories.PartyRepo;
import com.party.hoster.repositories.UserRepo;
import com.party.hoster.services.InvitationService;

@Service
public class InvitationServiceImpl implements InvitationService {
	
	@Autowired
    private InvitationRepo invitationRepo;

    @Autowired
    private PartyRepo partyRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

	@Override
	public InvitationDto sendInvitation(InvitationDto invitationDto,Integer partyId, int userId) {
		Party party = partyRepo.findById(partyId)
                .orElseThrow(() -> new ResourceNotFoundException("Party", "partyId", partyId));

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

        Invitation existingInvitation = invitationRepo.findByPartyAndUser(party, user);
        if (existingInvitation != null) {
            // Invitation already sent
            return this.modelMapper.map(existingInvitation, InvitationDto.class);
        }
        
        

        Invitation newInvitation = this.modelMapper.map(invitationDto, Invitation.class);
        newInvitation.setParty(party);
        newInvitation.setUser(user);
        newInvitation.setPostedDate(LocalDateTime.now());
        newInvitation.setStatus(InvitationStatus.PENDING); // Initially set to pending

        Invitation savedInvitation = invitationRepo.save(newInvitation);
        return this.modelMapper.map(savedInvitation, InvitationDto.class);
	}

	@Override
	public InvitationDto acceptInvitation(Integer invitationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rejectInvitation(Integer invitationId) {
		// TODO Auto-generated method stub

	}

}
