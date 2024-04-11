package com.party.hoster.controllers;



import com.party.hoster.payloads.MessageDto;
import com.party.hoster.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<MessageDto> sendMessage(@RequestBody MessageDto messageDto,
                                                  @RequestParam("senderId") Integer senderId,
                                                  @RequestParam("recieverId") Integer receiverId) {
        MessageDto sentMessage = messageService.createMessage(messageDto, senderId, receiverId);
        return new ResponseEntity<>(sentMessage, HttpStatus.CREATED);
    }

    @GetMapping("/sender/{senderId}")
    public ResponseEntity<List<MessageDto>> getMessagesBySenderId(@PathVariable("senderId") int senderId) {
        List<MessageDto> messages = messageService.getMessagesBySenderId(senderId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/receiver/{receiverId}")
    public ResponseEntity<List<MessageDto>> getMessagesByReceiverId(@PathVariable("receiverId") int receiverId) {
        List<MessageDto> messages = messageService.getMessagesByRecieverId(receiverId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Integer>> findMessagedUsersByUser(@PathVariable("userId") int userId) {
        List<Integer> messagedUsers = messageService.findMessagedUsersByUser(userId);
        return new ResponseEntity<>(messagedUsers, HttpStatus.OK);
    }
    //we can make it more secure by generating a secure token every time ...we can include a unique token to each profile which gets generated when any person logs in
    @GetMapping("/conversation")
    public ResponseEntity<List<MessageDto>> getConversation(
            @RequestParam("userId1") int userId1,
            @RequestParam("userId2") int userId2) {
        List<MessageDto> conversation = messageService.getConversation(userId1, userId2);
        return new ResponseEntity<>(conversation, HttpStatus.OK);
    }

   
}
