package com.example.jpa_test.response;

import com.example.jpa_test.user.domain.User;

public record UserResponse(
        Long id,
        String email,
        String username
) {
    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getUsername());
    }
}
