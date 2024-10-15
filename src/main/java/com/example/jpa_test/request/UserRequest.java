package com.example.jpa_test.request;

import com.example.jpa_test.user.domain.User;

public record UserRequest(
        String email,
        String password,
        String username
) {
    public User toEntity(){
        return User.builder()
                .email(email)
                .password(password)
                .username(username)
                .build();
    }
}
