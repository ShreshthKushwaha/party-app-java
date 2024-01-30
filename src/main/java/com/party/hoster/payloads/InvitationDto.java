package com.party.hoster.payloads;


import com.party.hoster.enums.InvitationStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class InvitationDto {
	 private Integer id;

	    @NotNull
	    private Integer partyId;

	    @NotNull
	    private int userId;

	    private InvitationStatus status;
	    
	    private PartyDto party;
	    
	    

}
