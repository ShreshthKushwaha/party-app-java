package com.party.hoster.payloads;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;



@NoArgsConstructor
@Getter
@Setter

public class PartyDto {

    private Integer partyId;

    //@NotNull
    //private Integer userId; // Assuming userId is required to associate the party with a user

    @NotBlank
    private String title;

    @NotBlank
    private String address;

    @NotBlank
    private String zip;

    @NotNull
    @Future(message = "Date must be in the future")
    private LocalDate partyDate;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    
    private String description;
    
    private UserDto user;

    // Constructors, getters, and setters

    // You may include additional validation annotations and methods based on your requirements
}

