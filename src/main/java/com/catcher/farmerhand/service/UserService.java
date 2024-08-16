package com.catcher.farmerhand.service;

import com.catcher.farmerhand.domain.Users;
import com.catcher.farmerhand.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean login(String email, String password) {
        Optional<Users> member = userRepository.findByEmail(email);
        if (member.isPresent() && member.get().getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
