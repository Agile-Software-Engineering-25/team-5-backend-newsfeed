package com.ase.newsfeedservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

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
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
    jwtConverter.setJwtGrantedAuthoritiesConverter(new JwtAuthConverter());

    // the role always has to be capitalized
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
        .requestMatchers("/**")
        .hasAnyAuthority("sau-admin", "university-administrative-staff")
        .anyRequest().denyAll())
        .oauth2ResourceServer(oauth2 -> oauth2
        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtConverter)));
    return http.build();
  }
}
