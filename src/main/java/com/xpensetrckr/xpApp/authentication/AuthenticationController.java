package com.xpensetrckr.xpApp.authentication;

import com.xpensetrckr.xpApp.registration.RegistrationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {
    private final AuthenticationServiceImpl authenticationService;

    public AuthenticationController(AuthenticationService authenticationService, AuthenticationServiceImpl authenticationService1) {
        this.authenticationService = authenticationService1;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

}
