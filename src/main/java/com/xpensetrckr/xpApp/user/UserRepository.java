package com.xpensetrckr.xpApp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    //    @Query("SELECT u FROM USER u WHERE u.email = ?1")
        Optional<User> findUserByEmail(String email);
        Optional<User> findByUsername(String username);
}
