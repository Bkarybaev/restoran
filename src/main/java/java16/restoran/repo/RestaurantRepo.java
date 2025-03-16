package java16.restoran.repo;

import java16.restoran.entity.Restaurant;
import java16.restoran.enums.RoleUser;
import java16.restoran.exceptions.NotFount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.management.relation.Role;

public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {
    @Query("select count(u) from Restaurant r join r.users u on u.role = :role")
    Integer findCountEmployees(RoleUser role);

    default Restaurant findRestaurantByException(Long id) {
        return findById(id).orElseThrow(
                () -> new NotFount("Restaurant not found with id: " + id));
    }
}
