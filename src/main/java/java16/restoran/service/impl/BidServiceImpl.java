package java16.restoran.service.impl;

import jakarta.transaction.Transactional;
import java16.restoran.dto.request.BidRequest;
import java16.restoran.entity.BidEmployees;
import java16.restoran.entity.Restaurant;
import java16.restoran.entity.User;
import java16.restoran.enums.RoleUser;
import java16.restoran.exceptions.ExceptionAge;
import java16.restoran.exceptions.NotFount;
import java16.restoran.exceptions.NumberTuuraEmes;
import java16.restoran.repo.BidRepo;
import java16.restoran.repo.RestaurantRepo;
import java16.restoran.repo.UserRepo;
import java16.restoran.service.BidService;
import java16.restoran.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BidServiceImpl implements BidService {
    private final UserRepo userService;

    private final PasswordEncoder passwordEncoder;
    private final RestaurantRepo restaurantRepo;
    private final BidRepo bidRepo;

    @Override
    public String saveBid(BidRequest bidRequest, Long restaurantId) {

        userService.findByEmail(bidRequest.getEmail())
                .orElseThrow(
                        () ->
                                new NotFount("User not found with email: "
                                        + bidRequest.getEmail() +
                                        " algach kattaluu kerek bolot"));

        BidEmployees byEmail = bidRepo.findByEmail(bidRequest.getEmail()).orElse(null);
        if (byEmail != null) {
            throw new NotFount("User with email " + byEmail.getEmail() + " already exists");
        }
        BidEmployees bidEmployees = new BidEmployees();

        int age = 25;
        int age2 = 45;
        String word = "povar";
        if (bidRequest.getWorkName().equalsIgnoreCase("POVAR")) {
            bidEmployees.setRole(RoleUser.POVAR);
        } else if (bidRequest.getWorkName().equalsIgnoreCase("WAITER")) {
            bidEmployees.setRole(RoleUser.WAITER);
            word = "ofisiant";
            age = 18;
            age2 = 30;
        }
        if (bidRequest.getAge() < age) {
            throw new ExceptionAge("Age is greater than "
                    + word + " age doljen "
                    + age + " bolshe years");
        } else if (bidRequest.getAge() > age2) {
            throw new ExceptionAge("Age is greater than "
                    + word + " age doljen "
                    + age + " menshe years");
        }

        if (bidRequest.getExperience() < 2) {
            int r = 2;
            if (bidRequest.getWorkName().equalsIgnoreCase("WAITER")) {
                if (bidRequest.getExperience() < 1) {
                    r = 1;
                }
            }
            throw new NumberTuuraEmes("Experience is greater than " + r + " bolshe years");
        }
        Restaurant restaurant = restaurantRepo.findRestaurantByException(restaurantId);
        restaurant.getBidEmployees().add(bidEmployees);
        bidEmployees.getRestaurant().add(restaurant);
        bidEmployees.setEmail(bidRequest.getEmail());
        bidEmployees.setPassword(passwordEncoder.encode(bidRequest.getPassword()));
        bidEmployees.setExperience(bidRequest.getExperience());
        bidEmployees.setAge(bidRequest.getAge());
        bidEmployees.setFirstName(bidRequest.getFirstName());
        bidEmployees.setPhone(bidRequest.getPhone());
        bidRepo.save(bidEmployees);
        return "success";
    }

    @Override
    @Transactional
    public String addEmployee(Long employeeId, String add, Long restaurantId) {
        BidEmployees byIdBidEmployee = bidRepo.findByIdBidEmployee(employeeId);
        User user = userService.findByEmail(byIdBidEmployee.getEmail()).orElse(null);
        assert user != null;
        Restaurant restaurant = restaurantRepo.findRestaurantByException(restaurantId);
        if (add.equalsIgnoreCase("add")) {
            user.setRole(byIdBidEmployee.getRole());
            restaurant.getUsers().add(user);
            user.setRestaurant(restaurant);
            restaurant.setNumberOrEmployees(restaurant.getNumberOrEmployees() + 1);
            restaurantRepo.save(restaurant);
            userService.save(user);
            return "success add";
        }
        bidRepo.delete(byIdBidEmployee);
        return "success remove";
    }
}
