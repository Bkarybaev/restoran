package java16.restoran.service;

import java16.restoran.dto.authrequest.LoginRequest;
import java16.restoran.dto.authrequest.RegisterRequest;
import java16.restoran.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    User findByEmail(String email);

    ResponseEntity<?> login(LoginRequest loginRequest);

    ResponseEntity<?> register(RegisterRequest registerRequest);
}
