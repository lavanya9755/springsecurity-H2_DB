package com.demo.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableMethodSecurity
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defauSecurityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable()); //csrf disble
        http.authorizeHttpRequests((authorizeHttpRequests) -> 
                                    authorizeHttpRequests.requestMatchers("/h2-console/**").permitAll().anyRequest().authenticated()
        );
        // http.oauth2Login(oauth2 -> oauth2.defaultSuccessUrl("http://localhost:3000/dashboard",true)); //git integeration
        // http.formLogin(form -> form.defaultSuccessUrl("/hello",true)); //basic shit it was
        // http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin())); //allowign headers frames
        return http.build();

    }

    //note: inmemoryuserdetailmanager extends userdetail manager and userdetailpassword
    //userdetailmanager extends userdetailService interface
    //therfore inmemeoryuserdetailmanager is implementation os userdetailService
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user1 = User.withUsername("user1")
                                .password("{noop}password1") //noop -prefix,used to save as plain text
                                .roles("USER")
                                .build();

        UserDetails admin = User.withUsername("admin")
                                .password("{noop}adminPass") 
                                .roles("ADMIN")
                                .build(); 
                                
        //create user in Database
        // JdbcUserDetailsManager userdet

        return new InMemoryUserDetailsManager(user1,admin);
    }

     
    

    
}
