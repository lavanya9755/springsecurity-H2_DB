package com.demo.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableMethodSecurity
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defauSecurityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable()); //csrf disble
        http.authorizeHttpRequests(auth -> auth // alllreq authenticates
                .requestMatchers("/oauth2/**").permitAll()
                .anyRequest().authenticated()
        );
        http.formLogin(form -> form.defaultSuccessUrl("/hello",true)); 
        return http.build();

    }
    
}
