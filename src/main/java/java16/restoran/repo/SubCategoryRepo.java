package java16.restoran.repo;

import java16.restoran.entity.Category;
import java16.restoran.entity.SubCategory;
import java16.restoran.exceptions.NotFount;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface SubCategoryRepo extends JpaRepository<SubCategory, Long> {
    @Query("select s from SubCategory s join fetch s.category")
    List<SubCategory> findAllWithCategory();

    default SubCategory findByIdException(Long id) {
       return findById(id).orElseThrow(
                () -> new NotFount("SubCategory with id " + id + " not found"));
    }

}
