package com.example.jpa_test.service;

import com.example.jpa_test.repository.UserRepository;
import com.example.jpa_test.request.UserRequest;
import com.example.jpa_test.response.UserResponse;
import com.example.jpa_test.user.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        return UserResponse.from(userRepository.save(userRequest.toEntity()));
    }

    @Override
    @Transactional
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow();
        user.update(userRequest);
//        user.setPassword(userRequest.password());
//        user.setUsername(userRequest.username()); setter 는 확장에 열려있으니 안쓰는게 좋다
        return UserResponse.from(user);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user != null) {
            userRepository.delete(user);
        }
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return UserResponse.from(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::from)
                .toList();
    }
}
