package com.fatec_api3.fatec_api3.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fatec_api3.fatec_api3.entities.User;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTAuthenticateFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;

  public JWTAuthenticateFilter(AuthenticationManager authenticationManager) throws Exception {
    this.authenticationManager = authenticationManager;
  }

  @Bean
  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException {
    try {
      User user = new ObjectMapper().readValue(request.getInputStream(), User.class);

      return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
          user.getEmail(), user.getPassword(), new ArrayList<>()));
    } catch (IOException e) {
      throw new RuntimeException("Error to authenticate the user", e);
    }
  }
}
