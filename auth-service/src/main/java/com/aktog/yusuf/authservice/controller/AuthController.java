package com.aktog.yusuf.authservice.controller;



import com.aktog.yusuf.authservice.dto.AuthRequest;
import com.aktog.yusuf.authservice.dto.CreateUserRequest;
import com.aktog.yusuf.authservice.model.User;
import com.aktog.yusuf.authservice.service.JwtService;
import com.aktog.yusuf.authservice.service.JwtTokenGenerator;
import com.aktog.yusuf.authservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtTokenGenerator jwtTokenGenerator;
    private final UserService userService;


    public AuthController(JwtTokenGenerator jwtTokenGenerator, UserService userService) {
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.userService = userService;
    }

    @PostMapping("/generate")
    public String generateJwtToken(@RequestBody @Valid AuthRequest request) {
        return jwtTokenGenerator.generateJwtToken(request);
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody @Valid CreateUserRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUserById(id));
    }


}
