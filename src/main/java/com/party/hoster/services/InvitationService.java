package com.party.hoster.services;

import java.util.List;

import com.party.hoster.payloads.InvitationDto;

public interface InvitationService {

	
	InvitationDto sendInvitation(InvitationDto invitationDto,Integer partyId, int userId);

    // Accept invitation
    InvitationDto acceptInvitation(Integer invitationId);

    // Reject invitation
    void rejectInvitation(Integer invitationId);
    
    //getting all invitations for a party
    List<InvitationDto> getInvitationsByParty(Integer partyId);
}
