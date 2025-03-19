package java16.restoran.repo;

import java16.restoran.entity.MenuItem;
import java16.restoran.exceptions.NotFount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.List;

public interface MenuItemRepo extends JpaRepository<MenuItem, Long> {
    @Query("""
    select m from MenuItem m
    join m.subCategory sc
    join sc.category c
    where lower(m.name) like lower(concat('%', :query, '%'))
       or lower(sc.name) like lower(concat('%', :query, '%'))
       or lower(c.name) like lower(concat('%', :query, '%'))
    """)

    List<MenuItem> searchMenuItems(String query);

    default MenuItem findByIdException(Long menuId) {
        return findById(menuId).orElseThrow(
                () -> new NotFount("Menu not found with id: " + menuId));
    }

    @Query("SELECT m FROM MenuItem m ORDER BY m.price ASC ")
    List<MenuItem> getAllSortByPrice();

    @Query("SELECT m FROM MenuItem m ORDER BY m.price DESC ")
    List<MenuItem>  getAllSortByPriceDESC();

    @Query("select m from MenuItem m where m.isVegetarian = :isTrue")
    List<MenuItem> findAllVegetarian(boolean isTrue);
}
