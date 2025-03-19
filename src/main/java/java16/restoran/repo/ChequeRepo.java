package java16.restoran.repo;

import java16.restoran.entity.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
@Repository
public interface ChequeRepo extends JpaRepository<Cheque, Long> {

        @Query("SELECT COALESCE(SUM(c.priceAverage), 0) FROM Cheque c " +
                "WHERE c.chequeUser.id = :waiterId AND FUNCTION('DATE', c.createdAt) = CURRENT_DATE")
        Optional<BigDecimal> getTotalAmountByWaiter(Long waiterId);

        @Query("SELECT COALESCE(AVG(c.priceAverage), 0) FROM Cheque c " +
                "WHERE FUNCTION('DATE', c.createdAt) = CURRENT_DATE")
        Optional<BigDecimal> getRestaurantDailyAverage();

}
