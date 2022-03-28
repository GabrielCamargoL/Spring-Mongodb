package com.fatec_api3.fatec_api3.security;

import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;

import javax.servlet.http.Cookie;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import com.fatec_api3.fatec_api3.entities.User;
import com.fatec_api3.fatec_api3.exceptions.TokenNotFoundException;
import com.fatec_api3.fatec_api3.services.UserDetailsData;

@Component
public class JWTUtil {
  @Autowired
  Environment env;

  private static String SECRET_KEY;
  private static final Long EXPIRATION_IN_MS = 10800000L;
  public static final String COOKIE_NAME = "jwtoken";

  @Value("${jwt.secretkey}")
  public void setSecretKey(String key) {
    JWTUtil.SECRET_KEY = key;
  }

  // gera token para user
  // Cria o token e define tempo de expiração pra ele
  public static String generateToken(User user) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();

    String jsonUser = mapper.writeValueAsString(user);

    return Jwts.builder().claim("userDetails", jsonUser).setIssuer("br.com.fatec")
        .setSubject(user.getEmail())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_IN_MS))
        .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
  }

  public static Authentication parseToken(String token) throws JsonParseException, JsonMappingException, IOException {
    if (token == null) {
      throw new TokenNotFoundException("Token not found");
    }
    ObjectMapper mapper = new ObjectMapper();
    String credentialsJson = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody()
        .get("userDetails", String.class);
    User user = mapper.readValue(credentialsJson, User.class);
    return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), new ArrayList<>());
  }

  public static String getTokenInCookies(Cookie[] cookies) {
    if (cookies == null)
      return null;
    for (Cookie cookie : cookies)
      if (cookie.getName().equals(JWTUtil.COOKIE_NAME))
        return cookie.getValue();
    return null;
  }
}
