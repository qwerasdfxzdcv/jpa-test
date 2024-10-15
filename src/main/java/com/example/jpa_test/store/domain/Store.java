package com.example.jpa_test.store.domain;

import com.example.jpa_test.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="STORES")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="STORE_ID")
    private Long id;
    private String name;
    private String address;
    @ManyToOne
    @JoinColumn (name = "USER_ID")
    private User user;
}
