package com.party.hoster.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity

@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	
	private int id;
	 
	@Column(nullable=false,length=100)
	private String fname;
	@Column(nullable=false,length=100)
	private String lname;
	private String address;
	@Column(nullable=false,length=100)
    private String phone;
	private String major;
	private String password;
	@Column(nullable=false,length=120)
	private String email;
	private String about;
	@Column(nullable=false,length=120)
	private LocalDate dateOfBirth; // Using LocalDate for date of birth
	
	private String zip;
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Party> parties = new ArrayList<>();
	@OneToMany(mappedBy = "user")
	private List<Invitation> invitations = new ArrayList<>();

}
