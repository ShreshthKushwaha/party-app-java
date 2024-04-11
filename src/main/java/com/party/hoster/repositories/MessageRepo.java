package com.party.hoster.repositories;


import com.party.hoster.entities.Message;
import com.party.hoster.entities.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



public interface MessageRepo extends JpaRepository<Message, Long> {
	
	List<Message> findBySender(User sender);
	
    List<Message> findByReciever(User reciever);
    
 

}
