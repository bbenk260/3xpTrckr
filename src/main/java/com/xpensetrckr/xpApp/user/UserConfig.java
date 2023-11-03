package com.xpensetrckr.xpApp.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository){
        return args -> {
            User bálint = new User(
                    "Bálint",
                    "valentin0519@gmail.com",
                    LocalDate.of(1994, MAY, 19)
            );
            User fanni = new User(
                    "Fanni",
                    "zoltaifanni94@gmail.com",
                    LocalDate.of(1994, DECEMBER, 28)
            );

            repository.saveAll(List.of(bálint,fanni));
        };
    }
}
