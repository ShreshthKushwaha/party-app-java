package com.party.hoster.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.party.hoster.entities.Message;
import com.party.hoster.entities.User;
import com.party.hoster.exceptions.ResourceNotFoundException;
import com.party.hoster.payloads.MessageDto;
import com.party.hoster.repositories.MessageRepo;
import com.party.hoster.repositories.UserRepo;
import com.party.hoster.services.MessageService;


@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private UserRepo userRepo;

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MessageDto createMessage(MessageDto messageDto,  Integer senderId, Integer recieverId) {
        // Convert MessageDto to Message entity using ModelMapper
    	User sender = this.userRepo.findById(senderId).orElseThrow(()->new ResourceNotFoundException("User","sender id",senderId));
    	User reciever = this.userRepo.findById(recieverId).orElseThrow(()->new ResourceNotFoundException("User","reciever id",recieverId));
        Message message = modelMapper.map(messageDto, Message.class);
        
        message.setReciever(reciever);
        message.setSender(sender);
        message.setTimestamp(LocalDateTime.now());
        // Save the message entity
        Message savedMessage = messageRepo.save(message);

        // Convert the saved message entity back to MessageDto using ModelMapper
        return modelMapper.map(savedMessage, MessageDto.class);
    }

    @Override
    public List<MessageDto> getAllMessages() {
         //Retrieve all messages from the repository
        List<Message> messages = messageRepo.findAll();

         //Convert Message entities to MessageDto using ModelMapper
        return messages.stream()
                .map(message -> modelMapper.map(message, MessageDto.class))
                .collect(Collectors.toList());
     }

    @Override
    public List<MessageDto> getMessagesBySenderId(int senderId) {
        // Retrieve messages sent by the specified sender ID
    	User sender = this.userRepo.findById(senderId).orElseThrow(()->new ResourceNotFoundException("User","sender id",senderId));
    	
        List<Message> messages = messageRepo.findBySender(sender);

        // Convert Message entities to MessageDto using ModelMapper
        return messages.stream()
                .map(message -> modelMapper.map(message, MessageDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MessageDto> getMessagesByRecieverId(int recieverId) {
        // Retrieve messages received by the specified receiver ID
    	
    	User reciever = this.userRepo.findById(recieverId).orElseThrow(()->new ResourceNotFoundException("User","sender id",recieverId));
        List<Message> messages = messageRepo.findByReciever(reciever);

        // Convert Message entities to MessageDto using ModelMapper
        return messages.stream()
                .map(message -> modelMapper.map(message, MessageDto.class))
                .collect(Collectors.toList());
    }

   

    @Override
    public List<Integer> findMessagedUsersByUser(int userId){
    	List<MessageDto> messagesSentToMe = getMessagesByRecieverId(userId);
    	List<MessageDto> messagesSentByMe = getMessagesBySenderId(userId);
    	
    	
    	Set<Integer> userIds = new HashSet<>();
        for (MessageDto message : messagesSentToMe) {
            userIds.add(message.getSender().getId());
        }
        for (MessageDto message : messagesSentByMe) {
            userIds.add(message.getReciever().getId());
        }

        // Convert the set of user IDs to a list for sorting
        List<Integer> uniqueUserIds = new ArrayList<>(userIds);
    	
    	
    	return uniqueUserIds;
    }
    
    @Override
    public List<MessageDto> getConversation(int userId1, int userId2) {
        // Retrieve messages sent to and by userId1
        List<MessageDto> messagesSentToUserA = getMessagesByRecieverId(userId1);
        List<MessageDto> messagesSentByUserA = getMessagesBySenderId(userId1);

        // Retrieve messages sent to and by userId2
        List<MessageDto> messagesSentToUserB = getMessagesByRecieverId(userId2);
        List<MessageDto> messagesSentByUserB = getMessagesBySenderId(userId2);

        // Combine messages from userId1 to userId2 and vice versa
        List<MessageDto> conversation = new ArrayList<>();
        
        conversation.addAll(messagesSentToUserA.stream()
                .filter(message -> message.getSender().getId() == userId2)
                .collect(Collectors.toList()));
        
        conversation.addAll(messagesSentByUserA.stream()
                .filter(message -> message.getReciever().getId() == userId2)
                .collect(Collectors.toList()));
        
        //conversation.addAll(messagesSentToUserB.stream()
         //       .filter(message -> message.getSender().getId() == userId1)
          //      .collect(Collectors.toList()));
        
      //  conversation.addAll(messagesSentByUserB.stream()
       //         .filter(message -> message.getReciever().getId() == userId1)
        //        .collect(Collectors.toList()));

        // Sort messages by timestamp
        conversation.sort(Comparator.comparing(MessageDto::getTimestamp));

        return conversation;
    }

    
    
}
