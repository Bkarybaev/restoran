package java16.restoran.repo;

import java16.restoran.api.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepo extends JpaRepository<SubCategory, Long> {
}
