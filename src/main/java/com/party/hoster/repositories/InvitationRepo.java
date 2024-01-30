package com.party.hoster.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.party.hoster.entities.Invitation;
import com.party.hoster.entities.Party;
import com.party.hoster.entities.User;

public interface InvitationRepo extends JpaRepository<Invitation,Integer>{
	
	Invitation findByPartyAndUser(Party party, User user);
	
	List<Invitation> findAllByParty(Party party);

	

}
