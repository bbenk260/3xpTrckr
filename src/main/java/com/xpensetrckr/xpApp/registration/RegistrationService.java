package com.xpensetrckr.xpApp.registration;

import org.springframework.transaction.annotation.Transactional;

public interface RegistrationService {
    public String register(RegistrationRequest request);
    public String confirmToken(String token);
}
