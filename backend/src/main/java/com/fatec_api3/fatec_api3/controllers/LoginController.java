package com.fatec_api3.fatec_api3.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fatec_api3.fatec_api3.entities.User;
import com.fatec_api3.fatec_api3.security.JWTAuthenticateFilter;
import com.fatec_api3.fatec_api3.security.JWTUtil;
import com.fatec_api3.fatec_api3.security.Login;
import com.fatec_api3.fatec_api3.services.UserDetailsData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  @Autowired
  AuthenticationManager authenticationManager;

  @RequestMapping("/login")
  @PostMapping()
  @CrossOrigin(exposedHeaders = "Token")
  public ResponseEntity<String> login(@RequestBody Login login, HttpServletResponse response)
      throws JsonProcessingException {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            login.getUsername(),
            login.getPassword()));

    User user = (User) authentication.getPrincipal();

    String jwt = JWTUtil.generateToken(user);

    return new ResponseEntity<String>(jwt, HttpStatus.OK);
  }
}
