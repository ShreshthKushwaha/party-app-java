package com.party.hoster.payloads;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter


public class UserDto {


	private int id;
	private String fname;
	private String lname;
	private String address;
    private String phone;
	private String major;
	private String password;
	private String email;
	private String about;
	private LocalDate dateOfBirth; // Using LocalDate for date of birth
	private String zip;

}
