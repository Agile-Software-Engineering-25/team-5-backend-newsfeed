package com.ase.newsfeedservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
Area-2.Team-5.Read.NewsPost-Engineering
Area-2.Team-5.Read.NewsPost-Business
Area-2.Team-5.Read.NewsPost-Chemistry
Area-2.Team-5.Read.NewsPost-ComputerScience
sau-admin
university-administrative-staff
student
lecturer
*/
@Configuration
@EnableMethodSecurity
@Profile("!dev")
public class ProdSecurityConfig {


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
    jwtConverter.setJwtGrantedAuthoritiesConverter(new JwtAuthConverter());

    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
        .requestMatchers("/**").permitAll())
        .oauth2ResourceServer(oauth2 -> oauth2
        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtConverter)));
    return http.build();
  }

  private Collection<GrantedAuthority> extractRealmRoles(Jwt jwt) {
    Map<String, Object> realmAccess = jwt.getClaim("realm_access");
    if (realmAccess == null) {
      return List.of();
    }

    Object rolesObj = realmAccess.get("roles");
    if (!(rolesObj instanceof List<?> roles)) {
      return List.of();
    }

    return roles.stream()
        .filter(r -> r instanceof String)
        .map(r -> new SimpleGrantedAuthority("" + r))
        .collect(Collectors.toList());
  }
}
