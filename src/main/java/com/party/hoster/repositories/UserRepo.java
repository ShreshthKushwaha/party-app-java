package com.party.hoster.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.party.hoster.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
