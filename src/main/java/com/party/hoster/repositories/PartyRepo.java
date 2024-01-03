package com.party.hoster.repositories;

import com.party.hoster.entities.Party;
import com.party.hoster.entities.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyRepo extends JpaRepository<Party, Integer> {
    // Custom query methods, if needed
	
	List<Party> findByUser(User user);
}
