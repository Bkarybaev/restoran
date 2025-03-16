package java16.restoran.service.impl;

import jakarta.transaction.Transactional;
import java16.restoran.dto.request.RestaurantRequest;
import java16.restoran.dto.response.RestaurantResponse;
import java16.restoran.entity.Restaurant;
import java16.restoran.enums.RestaurantType;
import java16.restoran.enums.RoleUser;
import java16.restoran.exceptions.NotFount;
import java16.restoran.exceptions.NumberTuuraEmes;
import java16.restoran.repo.RestaurantRepo;
import java16.restoran.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepo restaurantRepo;


    @Override
    public ResponseEntity<?> save(RestaurantRequest restaurant) {
        if (restaurant.getNumberOfPeople() <= 0){
            throw new NumberTuuraEmes("Number of people must be greater than 0");
        }
        Restaurant newrestaurant = new Restaurant();
        newrestaurant.setName(restaurant.getName());
        newrestaurant.setService(restaurant.getService());
        newrestaurant.setLocation(restaurant.getLocation());
        newrestaurant.setNumberOrEmployees(restaurant.getNumberOfPeople());
        newrestaurant.setRestType(RestaurantType.valueOf(restaurant.getResType()));
        restaurantRepo.save(newrestaurant);

        return ResponseEntity.ok("saved");
    }

    @Override
    public String delete(Long id) {
        return "poka ne delal";
    }

    @Override
    public ResponseEntity<?> update(Long id, RestaurantRequest restaurant) {
        Restaurant newrestaurant = restaurantRepo.findById(id).orElseThrow(
                ()-> new NotFount("Restaurant with id " + id + " not found"));
        newrestaurant.setName(restaurant.getName());
        newrestaurant.setService(restaurant.getService());
        newrestaurant.setLocation(restaurant.getLocation());
        newrestaurant.setNumberOrEmployees(restaurant.getNumberOfPeople());
        newrestaurant.setRestType(RestaurantType.valueOf(restaurant.getResType()));
        restaurantRepo.save(newrestaurant);
        return ResponseEntity.status(HttpStatus.OK).body(
                RestaurantRequest.builder()
                .name(newrestaurant.getName())
                .service(newrestaurant.getService())
                .location(newrestaurant.getLocation())
                        .numberOfPeople(newrestaurant.getNumberOrEmployees())
                        .resType(restaurant.getResType())
                .build()
        );
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        String vacansia = "вакансия бар";
        Restaurant restaurant = restaurantRepo.findById(id).orElseThrow(
                () -> new NotFount("Restaurant with id " + id + " not found"));
        Integer employees1 = restaurantRepo.findCountEmployees(RoleUser.POVAR);
        Integer employees2 = restaurantRepo.findCountEmployees(RoleUser.WAITER);
        if (restaurant.getNumberOrEmployees()
                >= employees1 + employees2) {
            vacansia = "вакансия жок";
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                RestaurantResponse.builder()
                        .name(restaurant.getName())
                        .service(restaurant.getService())
                        .location(restaurant.getLocation())
                        .numberOfPeople(restaurant.getNumberOrEmployees())
                        .resType(restaurant.getRestType().toString())
                        .description(vacansia)
                        .build()
        );
    }

}
