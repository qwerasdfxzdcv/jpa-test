package com.example.jpa_test.service;

import com.example.jpa_test.repository.UserRepository;
import com.example.jpa_test.request.UserRequest;
import com.example.jpa_test.response.UserResponse;
import com.example.jpa_test.user.domain.User;
import org.apache.coyote.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
// TDD(Test-Driven-Development) 테스트 주도 개발 <- 선 개발 후 테스트 방식이 아닌 선 테스트 후 개발 방식의 프로그래밍 방법
@DataJpaTest
class UserServiceTest {
    private UserServiceImpl userService;
    @Autowired
    private UserRepository userRepository;

    //Nested -> 테스트를 묶어서 한번에! 각 테스트마다 이름을 붙혀주면 한눈에 보기 더 편함
    @Nested
    class testAll {

        @Test
        void createUser() {
            UserRequest request = new UserRequest("pppp","oooo","iiii");
            // given
            userService.createUser(request);
            // when
            assertEquals(11, userRepository.count());
            // then
        }

        @Test
        void updateUser() {
            User user = users.get(0);
            UserRequest request = new UserRequest(user.getEmail(),
                    user.getPassword()+"1231",
                    user.getUsername());
            UserResponse response = userService.updateUser(user.getId(),request);

            assertNotNull(response);
            User after = userRepository.findById(user.getId()).get();
            assertEquals(request.password(), after.getPassword());
            assertEquals(request.username(), after.getUsername());
            assertEquals(request.email(), after.getEmail());
        }

        @Test
        @DisplayName("삭제성공")
        void deleteUserById() {
            userService.deleteUserById(1L);
            assertFalse(userRepository.findById(1L).isPresent());
        }

        @Test
        @DisplayName("성공")
        void getUserById() {
            Long id = users.get(0).getId();

            UserResponse userById = userService.getUserById(id);

            assertNotNull(userById);
            assertEquals(id, userById.id());
            assertEquals(users.get(0).getEmail(), userById.email());
        }

        @Test
        @DisplayName("실패: 못 찾는 경우 - NoSuchElementException 발생")
        void getUserById_failure_not_found() {
            Long id = 5123123L;

            assertThrows(NoSuchElementException.class,
                    () -> userService.getUserById(id));
        }

        @Test
        void getAllUsers() {
            List<UserResponse> allUsers = userService.getAllUsers();
            assertEquals(10, allUsers.size());
            assertEquals(users.get(0).getId(), allUsers.get(0).id());
        }

        String email = "www.@naver.com";
        String password = "1234";
        String username = "admin";
        List<User> users;

        @BeforeEach
        void setUp() {
            userService = new UserServiceImpl(userRepository);
            users = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                User user = User.builder()
                        .email(email + i)
                        .password(password)
                        .username(username + i)
                        .build();
                users.add(user);
            }
            userRepository.saveAll(users);
        }
    }
}