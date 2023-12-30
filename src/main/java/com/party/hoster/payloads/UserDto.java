package com.party.hoster.payloads;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter


public class UserDto {


	private int id;
	
//	@NotNull
//	private String fname;
//	@NotNull
//	private String lname;
//	@NotNull
//	private String address;
//	@NotNull
//    private String phone;
//	@NotNull
//	private String major;
//	@NotNull
//	private String password;
//	@Email
//	private String email;
//	
//	private String about;
//	
//	private LocalDate dateOfBirth; // Using LocalDate for date of birth
//	private String zip;
	@NotNull
    @NotBlank(message = "First name must not be blank")
    @Size(max = 100, message = "First name must be at most 100 characters")
    private String fname;

    @NotNull
    @NotBlank(message = "Last name must not be blank")
    @Size(max = 100, message = "Last name must be at most 100 characters")
    private String lname;

    @NotNull
    @NotBlank(message = "Address must not be blank")
    private String address;

    @NotNull
    @NotBlank(message = "Phone must not be blank")
    @Size(max = 20, message = "Phone must be at most 20 characters")
    private String phone;

    @NotNull
    @NotBlank(message = "Major must not be blank")
    private String major;

    @NotNull
    @NotBlank(message = "Password must not be blank")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @Email(message = "Email should be valid")
    private String email;

    private String about;

    private LocalDate dateOfBirth; // Using LocalDate for date of birth

    @NotBlank(message = "ZIP code must not be blank")
    private String zip;


}
