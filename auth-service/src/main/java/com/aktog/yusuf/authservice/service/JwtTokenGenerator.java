package com.aktog.yusuf.authservice.service;



import com.aktog.yusuf.authservice.dto.AuthRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class JwtTokenGenerator {
    /*
     * direkt jwt service e ekleyince circular dependency oluşuyor.
     */
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public JwtTokenGenerator(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public String generateJwtToken(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(request.username());
        }
        throw new UsernameNotFoundException(String.format("invalid username: %s", request.username()));

    }
}
