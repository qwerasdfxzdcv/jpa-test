package com.example.jpa_test.user.RepositoryTest;

import com.example.jpa_test.repository.UserRepository;
import com.example.jpa_test.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("빌더_테스트")
    void buildTest(){
        User user = User.builder()
                .email("asdf")
                .password("asdf")
                .username("fff")
                .stores(new ArrayList<>())
                .build();
        Assertions.assertEquals(new ArrayList<>(), user.getStores());
        assertEquals("asdf123", user.getEmail());
    }
}