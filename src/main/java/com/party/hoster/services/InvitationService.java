package com.party.hoster.services;

import com.party.hoster.payloads.InvitationDto;

public interface InvitationService {

	
	InvitationDto sendInvitation(InvitationDto invitationDto,Integer partyId, int userId);

    // Accept invitation
    InvitationDto acceptInvitation(Integer invitationId);

    // Reject invitation
    void rejectInvitation(Integer invitationId);
}
