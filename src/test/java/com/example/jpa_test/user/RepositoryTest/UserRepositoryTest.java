package com.example.jpa_test.user.RepositoryTest;

import com.example.jpa_test.repository.UserRepository;
import com.example.jpa_test.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


//@SpringBootTest // 스프링을 직접 실행해서 테스트 함
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    String email = "email1";
    String password = "pass";
    String username = "user";
    @BeforeEach
    void setUp(){
        User user = User.builder().email(email).password(password).username(username).build();
        userRepository.save(user);
    }


    @Test
    @DisplayName("빌더_테스트")
    void builderTest(){
        User user = User.builder()
                .email("fdsafd")
                .password("dsa")
                .username("fff")
                .build();
        assertEquals(new ArrayList<>(), user.getStores());
        assertEquals("fdsafd", user.getEmail());
    }
    @Test
    @Transactional
    void saveTest(){
        // given 이러한 값이 주어지고
        String email = "email22";
        User user = User.builder().email(email).password(password).username(username).build();

        // when 이거 실행했는데
        userRepository.save(user);

        // then 그 후 이런 값이랑 매칭이 되었다.
        assertEquals(email, user.getEmail());
        assertNotNull(user.getId());
    }
    @Test
    void 다섯개를_저장후_전부가_다섯개가_맞음을_테스트(){
        for (int i = 0; i < 4; i++) {
            User user = User.builder()
                    .email(email+i).password(password).username(username).build();
            userRepository.save(user);
        }

        List<User> all = userRepository.findAll();

        assertEquals(5, all.size());
        assertEquals(email+3, all.get(4).getEmail());
    }
    @Test
    void 이메일_email로_저장후_이메일로_찾아오는_테스트(){
        // given 이러한 값이 주어지고


        Optional<User> byEmail = userRepository.findByEmail(email);

        assertTrue(byEmail.isPresent());
        assertEquals(email, byEmail.get().getEmail());
    }
}