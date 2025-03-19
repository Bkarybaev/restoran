package java16.restoran.service;

import java16.restoran.dto.response.CheckResponse;

import java.math.BigDecimal;
import java.util.List;

public interface ChequeService {
    CheckResponse createCheck(Long waiterId, List<Long> menuItemIds);

    void updateCheck(Long id, List<Long> menuItemIds);

    void deleteCheck(Long id);

    BigDecimal getTotalAmountByWaiter(Long waiterId);

    BigDecimal getRestaurantDailyAverage();
}
