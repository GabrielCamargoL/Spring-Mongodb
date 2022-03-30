package com.fatec_api3.fatec_api3.controllers;

import java.util.List;
import java.util.Optional;

import com.fatec_api3.fatec_api3.entities.User;
import com.fatec_api3.fatec_api3.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.data.mongodb.core.query.Criteria;
// import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  UserRepository repositoryUser;

  MongoTemplate mongoTemplate;

  @GetMapping("/users")
  public List<User> getAllUsers() {
    List<User> users = repositoryUser.findAll();

    return users;
  }

  @GetMapping("/user/{userId}")
  public User getOneUser(@PathVariable String userId) {
    Optional<User> userOptional = repositoryUser.findById(userId);

    if (userOptional.isEmpty()) {

      return null;
    } else {

      User user = userOptional.get();
      return user;
    }
  }

  @PostMapping("/user")
  public User createUser(@RequestBody User reqUser) {
    
    User userExists = repositoryUser.findByEmail(reqUser.getEmail());
    
    if (userExists == null) {
      BCryptPasswordEncoder criptografar = new BCryptPasswordEncoder();
      String senhaCriptografada = criptografar.encode(reqUser.getPassword());
      reqUser.setPassword(senhaCriptografada);

      User user = repositoryUser.insert(reqUser);
      return user;

    } else {
      return null;
    }
  }

  // @PostMapping("/user")
  // public User createUser(@RequestBody User reqUser) {

  //   Query query = new Query();
  //   query.addCriteria(Criteria.where("email").is(reqUser.getEmail()));

  //   try {
  //     User usersExists = repositoryUser
  //         .findUserByEmail(reqUser.getEmail())
  //         .ifPresentOrElse(u -> {
  //           System.out.println(u + " já existe");

  //         }, () -> {
  //           repositoryUser.insert(reqUser);
  //         });

  //   } catch (Exception e) {
  //     System.out.println(e);
  //   }
  // }

  @PutMapping("/user/")
  public User updateUser(@RequestBody User reqUser) {

    Optional<User> userOptional = repositoryUser.findById(reqUser.getId());
    if (userOptional.isEmpty()) {
      return null;
    } else {
      User user = userOptional.get();
      repositoryUser.save(reqUser);
      return user;

    }
  }
}
