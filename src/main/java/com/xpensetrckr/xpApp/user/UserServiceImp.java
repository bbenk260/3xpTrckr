package com.xpensetrckr.xpApp.user;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addNewUser(User user) {
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());

        if (userOptional.isPresent()) {
            throw new IllegalStateException("Email is taken");
        } else {
            userRepository.save(user);
            System.out.println(user);
        }
    }

    @Override
    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);

        if (!exists){
            throw new IllegalStateException("User with id " + userId + " does not exists");
        } else {
            userRepository.deleteById(userId);
        }
    }

    @Override
    @Transactional
    public void updateUser(Long userId, String name, String email) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException(
                "User with id " + userId + " does not exists")
        );

        if (name != null &&
        name.length() > 0 &&
        !Objects.equals(user.getName(),name)) {
            user.setName(name);
        }

        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(user.getEmail(),email)) {
            Optional<User> userOptional = userRepository.findUserByEmail(email);

            if (userOptional.isPresent()){
                throw new IllegalStateException("Email is taken");
            }
        } else {
            user.setEmail(email);
        }
    }
}
