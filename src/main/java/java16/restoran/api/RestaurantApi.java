package java16.restoran.api;

import jakarta.validation.Valid;
import java16.restoran.dto.request.RestaurantRequest;
import java16.restoran.service.impl.RestaurantServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/restaurant")
public class RestaurantApi {
    private final RestaurantServiceImpl restaurantService;

    /// crud method
    //creat
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<?> saveRestaurant(@RequestBody @Valid RestaurantRequest restaurant) {
        return restaurantService.save(restaurant);
    }
    //update
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRestaurant(
            @PathVariable Long id,
            @RequestBody @Valid RestaurantRequest restaurant) {
       return restaurantService.update(id,restaurant);
    }

    //get by id
    @PreAuthorize("hasAnyAuthority('ADMIN','CLIENT')")
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getRestaurant(@PathVariable Long id) {
        return restaurantService.getById(id);
    }

    //delete by id
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public String deleteRestaurant(@PathVariable Long id) {
        return restaurantService.delete(id);
    }
}
