package com.fatec_api3.fatec_api3.repositories;

import java.util.List;

import com.fatec_api3.fatec_api3.entities.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
  User findByEmail(String email);
}
