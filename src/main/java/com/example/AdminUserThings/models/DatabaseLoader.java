package com.example.AdminUserThings.models;

import com.example.AdminUserThings.services.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DatabaseLoader {

    private UserRepository repo;

    public DatabaseLoader(UserRepository repo) {
        this.repo = repo;
    }

    @Bean
    public CommandLineRunner initializeDatabase(){
        return args -> {
            User user1 = new User("deneme1@gmail.com","admin123");
            User user2 = new User("deneme2@gmail.com","david123");

            repo.saveAll(List.of(user1,user2));

            System.out.println("Sample database initialized");
        };
    }
}
