package java16.restoran.service;

import jakarta.validation.Valid;
import java16.restoran.dto.request.RestaurantRequest;
import org.springframework.http.ResponseEntity;

public interface RestaurantService {
    ResponseEntity<?> save(RestaurantRequest restaurant);

    String delete(Long id);

    ResponseEntity<?> update(Long id, @Valid RestaurantRequest restaurant);

    ResponseEntity<?> getById(Long id);
}
