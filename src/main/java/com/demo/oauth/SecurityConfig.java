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
        http.authorizeHttpRequests(authorizeHttpRequests -> 
        authorizeHttpRequests.anyRequest().authenticated()
        );
        http.oauth2Login(oauth2 -> oauth2.defaultSuccessUrl("http://localhost:3000/dashboard",true)); //git integeration
        // http.formLogin(form -> form.defaultSuccessUrl("/hello",true)); //basic shit it was

        return http.build();

    }
    
}
