package java16.restoran.repo;

import java16.restoran.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    @Query("select u from User u where u.email = :email")
   Optional<User>  findByEmail(String email);
}
