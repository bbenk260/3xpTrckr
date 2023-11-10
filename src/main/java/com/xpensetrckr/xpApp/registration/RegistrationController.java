package com.xpensetrckr.xpApp.registration;

import com.xpensetrckr.xpApp.authentication.AuthenticationServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class RegistrationController {
    private final RegistrationServiceImp registrationService;

    public RegistrationController(RegistrationServiceImp registrationService) {
        this.registrationService = registrationService;
    }
    @PostMapping("/registration")
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping("/registration/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
