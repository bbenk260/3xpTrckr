package com.xpensetrckr.xpApp.user;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //    @Query("SELECT u FROM USER u WHERE u.email = ?1")
    Optional<User> findUserByEmail(String email);

    Optional<User> findByUsername(String username);

  /*  @Transactional
    @Modifying
    @Query("UPDATE User u SET u.enabled = TRUE WHERE u.username = :username")
    int enableUser(@Param("username") String username);
  */
}
