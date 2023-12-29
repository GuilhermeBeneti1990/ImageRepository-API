package io.github.guilhermebeneti1990.imageliteapi.domain.services;

import io.github.guilhermebeneti1990.imageliteapi.domain.auth.AccessToken;
import io.github.guilhermebeneti1990.imageliteapi.domain.entities.User;

public interface UserService {
    User getByEmail(String email);
    User save(User user);
    AccessToken authenticate(String email, String password);

}
