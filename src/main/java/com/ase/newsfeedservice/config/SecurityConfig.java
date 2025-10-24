package com.ase.newsfeedservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

// expected jwt
// {
//   "sub": "user123",
//   "groups": ["admin", "editor"],
//   "exp": 1734300000
// }


/* 
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${spring.profiles.active:}")
    private String activeProfile;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        if ("dev".equalsIgnoreCase(activeProfile)) {
            // Disable security in dev
            http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        } else {
            // Normal JWT protection
            http
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/admin/**").hasAuthority("GROUP_admin")
                    .requestMatchers("/editor/**").hasAuthority("GROUP_editor")
                    .requestMatchers("/posts/**").authenticated()
                    .anyRequest().denyAll()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                    .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                );
        }
        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
        authoritiesConverter.setAuthoritiesClaimName("groups");
        authoritiesConverter.setAuthorityPrefix("GROUP_");

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);
        return converter;
    }
}
*/