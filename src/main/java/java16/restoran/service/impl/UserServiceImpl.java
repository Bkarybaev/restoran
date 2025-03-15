package java16.restoran.service.impl;

import jakarta.annotation.PostConstruct;
import java16.restoran.config.jwt.JwtService;
import java16.restoran.dto.authrequest.LoginRequest;
import java16.restoran.dto.authrequest.RegisterRequest;
import java16.restoran.dto.response.AuthResponse;
import java16.restoran.entity.User;
import java16.restoran.enums.RoleUser;
import java16.restoran.exceptions.NotFount;
import java16.restoran.exceptions.PasswordTuuraEmes;
import java16.restoran.repo.UserRepo;
import java16.restoran.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public ResponseEntity<?> login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        User byEmail = userRepo.findByEmail(email).orElseThrow(
                () -> new NotFount("User not found by email: " + email));

        boolean matches =
                passwordEncoder.matches(
                        loginRequest.getPassword(),
                        byEmail.getPassword());
        if (!matches) {
            throw new PasswordTuuraEmes("Wrong password");
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                AuthResponse.builder()
                        .token(jwtService.createToken(byEmail))
                        .email(email)
                        .role(byEmail.getRole())
                        .build()
        );
    }

    @Override
    public ResponseEntity<?> register(RegisterRequest registerRequest) {
        if (userRepo.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setRole(RoleUser.CLIENT);
        user.setDateOfBirth(registerRequest.getBirthDate());
        user.setFirstName(registerRequest.getFistName());
        user.setLastName(registerRequest.getLastName());
        user.setPhone(registerRequest.getPhone());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        User save = userRepo.save(user);
        boolean matcher =  passwordEncoder.matches(
                registerRequest.getPassword(),
                save.getPassword());
        if (!matcher) {
            throw new PasswordTuuraEmes("Wrong password");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(
                AuthResponse.builder()
                        .token(jwtService.createToken(save))
                        .email(save.getEmail())
                        .role(save.getRole())
                        .build()
        );
    }

    @PostConstruct
    public void init() {
        String email = "admin@gmail.com";
        if (userRepo.findByEmail(email).isEmpty()) {
            User user = new User();
            user.setRole(RoleUser.ADMIN);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode("Admin123!"));
            userRepo.save(user);
        }

    }
}
