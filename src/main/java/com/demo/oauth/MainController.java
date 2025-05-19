package com.demo.oauth;

import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/user-info")
    public Map<String,Object> user(@AuthenticationPrincipal OAuth2User principle){  //what is this ??annotation
           

        return principle.getAttributes();

    }
    
}
