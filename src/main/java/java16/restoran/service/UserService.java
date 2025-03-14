package java16.restoran.service;

import java16.restoran.entity.User;

public interface UserService {
    User findByEmail(String email);
}
