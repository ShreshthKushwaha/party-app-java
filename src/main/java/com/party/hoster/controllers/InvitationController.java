package com.party.hoster.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.party.hoster.payloads.InvitationDto;
import com.party.hoster.services.InvitationService;

@RestController
@RequestMapping("/api/")
public class InvitationController {

    @Autowired
    private InvitationService invitationService;

   //making an invitation request from the user

    @PostMapping("/parties/{partyId}/invitations/{userId}")
    public ResponseEntity<InvitationDto> sendInvitation(
            @RequestBody InvitationDto invitationDto,
            @PathVariable Integer partyId,
            @PathVariable Integer userId) {

        InvitationDto sentInvitation = this.invitationService.sendInvitation(invitationDto, partyId, userId);
        return new ResponseEntity<InvitationDto>(sentInvitation, HttpStatus.CREATED);
    }
}
