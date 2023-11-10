package com.xpensetrckr.xpApp.authentication;

import com.xpensetrckr.xpApp.registration.RegistrationRequest;

public interface AuthenticationService {
    public AuthenticationResponse register(RegistrationRequest registrationRequest);
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
