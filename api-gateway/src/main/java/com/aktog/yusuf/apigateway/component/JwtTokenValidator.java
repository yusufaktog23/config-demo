package com.aktog.yusuf.apigateway.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


@Component
public class JwtTokenValidator {
    @Value("${jwt.secret}")
    private String secret;

/*
    public Date extractExpiry(String token) {
        return getJwtBody(token).getExpiration();
    }

    public String extractUserName(String token) {
        return getJwtBody(token).getSubject();
    }

    public Claims getJwtBody(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
*/

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public void validateToken(String token) {
        String jwtToken = token.substring(7);
        Date date = Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody()
                .getExpiration();
        System.out.println("date => " + date);
    }
}
