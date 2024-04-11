package com.party.hoster.entities;

import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="messages")
@Getter
@Setter
public class Message {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //one sender can recieve many messages
	@ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;
    
	@ManyToOne
    @JoinColumn(name = "reciever_id")
    private User reciever;
    
    
    @Column(nullable=false)
    private String content;
    private LocalDateTime timestamp;

    // Getters and setters
}
