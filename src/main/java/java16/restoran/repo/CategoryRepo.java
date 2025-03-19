package java16.restoran.repo;

import java16.restoran.entity.Category;
import java16.restoran.exceptions.NotFount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    default Category findByIdException(Long id){
        return findById(id).orElseThrow(
                () -> new NotFount("Category not found for id " + id));
    }
}
