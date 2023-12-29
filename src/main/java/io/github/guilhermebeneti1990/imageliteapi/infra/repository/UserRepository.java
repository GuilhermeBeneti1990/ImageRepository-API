package io.github.guilhermebeneti1990.imageliteapi.infra.repository;

import io.github.guilhermebeneti1990.imageliteapi.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}
