package io.github.guilhermebeneti1990.imageliteapi.application.users;

import io.github.guilhermebeneti1990.imageliteapi.application.jwt.JwtService;
import io.github.guilhermebeneti1990.imageliteapi.domain.auth.AccessToken;
import io.github.guilhermebeneti1990.imageliteapi.domain.entities.User;
import io.github.guilhermebeneti1990.imageliteapi.domain.exceptions.DuplicatedDataException;
import io.github.guilhermebeneti1990.imageliteapi.domain.services.UserService;
import io.github.guilhermebeneti1990.imageliteapi.infra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    @Override
    public User getByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    @Transactional
    public User save(User user) {
        var userExists = getByEmail(user.getEmail());
        if(userExists != null) {
            throw new DuplicatedDataException("User already exists!");
        }
        encodePassword(user);
        return repository.save(user);
    }

    @Override
    public AccessToken authenticate(String email, String password) {
        var user = getByEmail(email);
        if(user == null) {
            return null;
        }
        boolean matchPassword = encoder.matches(password, user.getPassword());
        if(matchPassword) {
            return jwtService.generateToken(user);
        }
        return null;
    }

    private void encodePassword(User user) {
        var password = user.getPassword();
        var encodedPassword = encoder.encode(password);
        user.setPassword(encodedPassword);
    }
}
