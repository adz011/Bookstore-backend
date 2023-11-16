package com.bookstore.auth;

import com.bookstore.exceptions.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @RequestBody RegisterRequest request
    ) {
        try {
            AuthenticationResponse authenticationResponse = authenticationService.register(request);
            return ResponseEntity.ok(authenticationResponse);
        } catch (UserAlreadyExistsException e) {
            String errorMessage = e.getMessage();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(
            @RequestBody AuthenticationRequest request
    ) {
        try {
            AuthenticationResponse authenticationResponse = authenticationService.authenticate(request);
            return ResponseEntity.ok(authenticationResponse);
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            String errorMessage = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }
}
