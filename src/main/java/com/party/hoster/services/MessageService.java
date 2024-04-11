package com.party.hoster.services;

import java.util.List;


import com.party.hoster.payloads.MessageDto;

public interface MessageService {

    // Create
    MessageDto createMessage(MessageDto messageDto,  Integer senderId, Integer recieverId);

    // Get all messages
   // List<MessageDto> getAllMessages();

    // Get messages sent by a specific user
    List<MessageDto> getMessagesBySenderId(int senderId);

    // Get messages received by a specific user
    List<MessageDto> getMessagesByRecieverId(int recieverId);

    // Get coversations with users with our user
    List<Integer> findMessagedUsersByUser(int userId);
    
    //Get all messages
    List<MessageDto> getAllMessages();
    
    //get conversation
    List<MessageDto> getConversation(int userId1,int userId2);
    
    

 

    // Other methods as needed
}