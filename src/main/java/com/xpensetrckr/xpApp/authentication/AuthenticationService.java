package com.xpensetrckr.xpApp.authentication;

public interface AuthenticationService {
    public AuthenticationResponse register(RegisterRequest registerRequest);
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
