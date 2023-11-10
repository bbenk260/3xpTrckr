package com.xpensetrckr.xpApp.user;

import com.xpensetrckr.xpApp.security.config.confirmation_token.ConfirmationToken;
import com.xpensetrckr.xpApp.security.config.confirmation_token.ConfirmationTokenService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

    @Autowired
    public UserServiceImp(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ConfirmationTokenService confirmationTokenService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.confirmationTokenService = confirmationTokenService;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addNewUser(User user) {
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());

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

        if (!exists) {
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
                !Objects.equals(user.getUsername(), name)) {
            user.setUsername(name);
        }

        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(user.getEmail(), email)) {
            Optional<User> userOptional = userRepository.findUserByEmail(email);

            if (userOptional.isPresent()) {
                throw new IllegalStateException("Email is taken");
            }
        } else {
            user.setEmail(email);
        }
    }

    @Override
    public String register(User user) {
        boolean userExists = userRepository
                .findByUsername(user.getUsername())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("Username is already taken!");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

//        TODO: SEND EMAIL
        return token;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, username)));
    }

  /*  public int enableUser(String username) {
        return userRepository.enableUser(username);
    }*/

    @Transactional
    public void enableUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("User not found"));
        // Perform the necessary updates
        user.setEnabled(true);
        userRepository.save(user);
    }
}
