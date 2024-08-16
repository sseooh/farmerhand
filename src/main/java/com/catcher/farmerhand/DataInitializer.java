package com.catcher.farmerhand;

import com.catcher.farmerhand.domain.Users;
import com.catcher.farmerhand.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public ApplicationRunner initializer(UserRepository userRepository) {
        return args -> {
            userRepository.save(new Users("test1@naver.com", "12345"));
            userRepository.save(new Users("test2@naver.com", "12345"));
        };
    }
}
