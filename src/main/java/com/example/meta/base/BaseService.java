package com.example.meta.base;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
class BaseService {
    private final UserRepository userRepository;

    BaseService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    void save(String username, String email) {
        userRepository.findUserByEmail(email).ifPresentOrElse(user -> {
            user.setUsername(username);
        }, () -> {
            userRepository.save(new User().setUsername(username).setEmail(email));
        });
    }

    List<User> getUsers() {
        return userRepository.findAll();
    }

    @Cacheable(value = "user", key = "#email")
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).get();
    }
}

interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
}

@Entity
@Table(name = "userinfo")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
}
