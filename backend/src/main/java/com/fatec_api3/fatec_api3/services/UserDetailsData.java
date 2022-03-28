package com.fatec_api3.fatec_api3.services;

import java.util.Collection;
import java.util.Optional;

import com.fatec_api3.fatec_api3.entities.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsData implements UserDetails {

  private final Optional<User> user;

  public UserDetailsData(Optional<User> user) {
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return user.orElse(new User()).getPassword();
  }

  @Override
  public String getUsername() {
    return user.orElse(new User()).getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
