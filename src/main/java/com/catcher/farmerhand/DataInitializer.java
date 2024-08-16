package com.catcher.farmerhand;

import com.catcher.farmerhand.domain.Board;
import com.catcher.farmerhand.domain.Type;
import com.catcher.farmerhand.domain.Users;
import com.catcher.farmerhand.repository.BoardRepository;
import com.catcher.farmerhand.repository.TypeRepository;
import com.catcher.farmerhand.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public ApplicationRunner initializer(UserRepository userRepository, TypeRepository typeRepository, BoardRepository boardRepository) {
        return args -> {
            // Users 데이터 생성
            Users user1 = userRepository.save(new Users("test1@naver.com", "12345"));
            Users user2 = userRepository.save(new Users("test2@naver.com", "12345"));

            // Type 데이터 생성
            Type programType = typeRepository.save(new Type("프로그램"));
            Type policyType = typeRepository.save(new Type("정책"));

            // Board 데이터 생성
            boardRepository.save(new Board("프로그램 제목 1", "프로그램 설명 1", programType, user1));
            boardRepository.save(new Board("프로그램 제목 2", "프로그램 설명 2", programType, user2));
            boardRepository.save(new Board("정책 제목 1", "정책 설명 1", policyType, user1));
            boardRepository.save(new Board("정책 제목 2", "정책 설명 2", policyType, user2));
        };
    }
}
