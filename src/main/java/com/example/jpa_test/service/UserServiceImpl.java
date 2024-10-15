package com.example.jpa_test.service;

import com.example.jpa_test.repository.UserRepository;
import com.example.jpa_test.request.UserRequest;
import com.example.jpa_test.response.UserResponse;
import com.example.jpa_test.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        return null;
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        return null;
    }

    @Override
    public void deleteUserById(Long id) {

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
