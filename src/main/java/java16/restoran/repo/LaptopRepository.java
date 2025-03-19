package java16.restoran.repo;

import java16.restoran.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface LaptopRepository extends JpaRepository<Laptop, Long> {
}
