package com.example.jpa_test.user.RepositoryTest;

import com.example.jpa_test.repository.UserRepository;
import com.example.jpa_test.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest 스프링을 직접 실행해서 테스트 함
@DataJpaTest
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
    @Test
    void saveTest(){
        // given 이러한 값이 주어지고
        String email = "email";
        String password = "pass";
        String username = "user";

        User user = User.builder()
                .email(email)
                .password(password)
                .username(username)
                .build();
        // when 이걸 실행하면
        userRepository.save(user); //id가 자동으로 생성된다!!
        // then 그 후 이렇게 매칭되었다.
        assertEquals(email, user.getEmail());
        assertNotNull(user.getId());
    }
    @Test
    void 다섯개를_저장후_전부가_다섯개가_맞음을_테스트(){
        String email = "email";
        String password = "pass";
        String username = "user";

        for(int i=0;i<5;i++){
            User user = User.builder()
                    .email(email+i)
                    .password(password)
                    .username(username)
                    .build();
            userRepository.save(user);
        }
        assertEquals(5, userRepository.count());
    }
    @Test
    void 이메일_email로_저장후_이메일로_찾아오는_테스트(){
        String email = "emails";
        String password = "pass";
        String username = "user";
        User user = User.builder()
                .email(email)
                .password(password)
                .username(username)
                .build();
        userRepository.save(user);
        Optional<User> byEmail = userRepository.findByEmail(email);

        assertTrue(byEmail.isPresent());
        assertEquals(email, byEmail.get().getEmail());
    }
}