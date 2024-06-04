package com.example.muslis.models;

import com.example.muslis.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role userRole;
    @OneToOne(mappedBy = "userInfo", cascade = CascadeType.ALL)
    private Artist artistPart;
    @OneToOne(mappedBy = "userInfo", cascade = CascadeType.ALL)
    private Listener listenerPart;

}
