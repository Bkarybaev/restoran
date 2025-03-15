package java16.restoran.api;

import jakarta.validation.Valid;
import java16.restoran.dto.authrequest.LoginRequest;
import java16.restoran.dto.authrequest.RegisterRequest;
import java16.restoran.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApi {
    private final UserService userService;


    //login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    //register
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest registerRequest) {
       return userService.register(registerRequest);
    }

}
