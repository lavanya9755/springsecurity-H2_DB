package com.demo.oauth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableMethodSecurity
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    //note: this is used to connect to database,H2
    @Autowired
    DataSource datasource;

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
                                .password(passwordEncoder().encode("password1")) //noop -prefix,used to save as plain text
                                .roles("USER")
                                .build();

        UserDetails admin = User.withUsername("admin")
                                .password(passwordEncoder().encode("adminPass"))
                                .roles("ADMIN")
                                .build(); 
                                
        //create user in Database using jdbc ,takes Darasource as parameter in Constr
        JdbcUserDetailsManager userdetailmanager = new JdbcUserDetailsManager(datasource);
        userdetailmanager.createUser(user1);
        userdetailmanager.createUser(admin);

        return userdetailmanager;


        // return new InMemoryUserDetailsManager(user1,admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

     
    

    
}
