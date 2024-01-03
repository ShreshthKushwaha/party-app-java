package com.party.hoster.entities;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="parties")
@Getter
@Setter
@NoArgsConstructor
public class Party {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer partyId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String title;
    private String address;
    private String zip;
    private LocalDateTime postedDate;
    private LocalDate partyDate;
    private LocalTime startTime;
    private LocalTime endTime;
    @Column(length =10000)
    private String description;
	

}
