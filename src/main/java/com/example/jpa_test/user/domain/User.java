package com.example.jpa_test.user.domain;

import ch.qos.logback.core.util.StringUtil;
import com.example.jpa_test.request.UserRequest;
import com.example.jpa_test.store.domain.Store;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Table(
        name = "USERS",
        indexes = {
                @Index(columnList = "email"),
                @Index(columnList = "username")
        }
)

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id @Column(name = "USER_ID")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(length = 10, nullable = false)
    private String username;
    @OneToMany (mappedBy = "user")
    private List<Store> stores = new ArrayList<>();
    public void update(UserRequest request){
        if(!StringUtil.isNullOrEmpty(request.password()))
            this.password = request.password();
        if(!StringUtil.isNullOrEmpty(request.username()))
            this.username = request.username();
    }
}
