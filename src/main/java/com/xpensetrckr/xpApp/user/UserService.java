package com.xpensetrckr.xpApp.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getUsers();
    void addNewUser(User user);

    void deleteUser(Long id);

    void updateUser(Long id, String name, String email);

    String register(User user);

    void enableUser(String username);
}
