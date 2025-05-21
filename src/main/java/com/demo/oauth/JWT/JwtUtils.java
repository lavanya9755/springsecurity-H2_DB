package com.demo.oauth.JWT;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;

import javax.crypto.SecretKey;

import java.security.Key;
import java.util.Base64.Decoder;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtils {
    //Spring will look for a property named spring.app.jwtSecret and inject its value into the jwtSecret field.
    @Value("${spring.app.jwtSecret}")
    private String jwtSecret;

    @Value("${spring.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String getJwtFromHeader(HttpServletRequest request){
        String jwt = request.getHeader("Authorization");
        if (jwt != null && jwt.startsWith("Bearer ")) {
            return jwt.substring(7);
        }
        return null;
    }

    public String generateTokenFromUsername(UserDetails userDetails){
        String username = userDetails.getUsername();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key())
                .compact();
        
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser()
                .verifyWith((SecretKey)key())
                .build()
                .parseSignedClaims(token)
                .getPayload()          
                .getSubject();
    }
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public boolean validateJwtToken(String authToken){
        try {
            System.out.println("Validate");
            Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(authToken);
            return true;
        } catch (Exception e) {
              e.getMessage();
        }

        return false;

    }
}

    
