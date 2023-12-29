package io.github.guilhermebeneti1990.imageliteapi.application.users;

import io.github.guilhermebeneti1990.imageliteapi.domain.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToUser(UserDTO dto) {
        return User.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .password(dto.getPassword())
                .build();
    }

}
