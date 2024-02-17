package io.novelis.onlineblog.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name="`users`")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(fetch = FetchType.LAZY,  mappedBy = "user")
    private List<Article> articles;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    // Getters and setters
    // Constructors
    // Other methods
}
