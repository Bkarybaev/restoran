package java16.restoran.repo;

import java16.restoran.entity.BidEmployees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BidRepo extends JpaRepository<BidEmployees, Long> {
    @Query("select b from BidEmployees b where b.email = :email")
    Optional<BidEmployees> findByEmail(String email);

    default BidEmployees findByIdBidEmployee(Long id){
        return findById(id).orElseThrow(
                () -> new RuntimeException("Bid not found with id: " + id));
    }
}
