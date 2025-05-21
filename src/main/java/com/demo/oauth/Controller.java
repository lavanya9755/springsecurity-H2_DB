package com.demo.oauth;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class Controller {

    @GetMapping("/hello")
    public String sayHellp() {
        return "hello";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String userEndpoint() {
        return "hello user!";
    }
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')") //security before method
    public String adminEndpoint() {
        return "hello admin!";
    }
    //note: there s one more annotation,PostAuthorize() were security will be doen after method result 
    
    
}
