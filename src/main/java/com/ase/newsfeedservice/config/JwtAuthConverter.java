package com.ase.newsfeedservice.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.lang.NonNull;
import java.util.Collection;
import java.util.stream.Collectors;

public class JwtAuthConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

  @Override
  public Collection<GrantedAuthority> convert(@NonNull Jwt jwt) {
    var roles = jwt.getClaimAsStringList("groups");
    return roles.stream()
        .map(role -> new SimpleGrantedAuthority("" + role))
        .collect(Collectors.toList());
  }
}
