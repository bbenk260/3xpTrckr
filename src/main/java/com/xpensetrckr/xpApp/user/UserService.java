package com.xpensetrckr.xpApp.user;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    void addNewUser(User user);

    void deleteUser(Long id);

    void updateUser(Long id, String name, String email);
}
