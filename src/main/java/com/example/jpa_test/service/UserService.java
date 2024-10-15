package com.example.jpa_test.service;

import com.example.jpa_test.request.UserRequest;
import com.example.jpa_test.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
    UserResponse updateUser(Long id, UserRequest userRequest);
    void deleteUserById(Long id);
    UserResponse getUserById(Long id);
    List<UserResponse> getAllUsers();
}
