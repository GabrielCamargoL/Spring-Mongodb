package com.fatec_api3.fatec_api3.entities;

import java.time.LocalDateTime;

import com.fatec_api3.fatec_api3.enums.Gender;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document()
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  private String id;
  private String firstName;
  private String lastName;
  @Indexed(unique = true)
  private String email;
  private String password;
  private Gender gender;
  private String country;
  private String zipCode;
  private String city;
  private LocalDateTime created;
  private LocalDateTime updated;

  public User(
      String firstName,
      String lastName,
      String email,
      String password,
      Gender gender,
      String country,
      String zipCode,
      String city,
      LocalDateTime created,
      LocalDateTime updated) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.gender = gender;
    this.country = country;
    this.zipCode = zipCode;
    this.city = city;
    this.created = created;
    this.updated = updated;
  }
}
