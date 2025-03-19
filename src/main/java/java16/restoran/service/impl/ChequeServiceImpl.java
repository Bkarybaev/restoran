package java16.restoran.service.impl;

import jakarta.transaction.Transactional;
import java16.restoran.dto.response.CheckResponse;
import java16.restoran.dto.response.MenuResponse;
import java16.restoran.entity.Cheque;
import java16.restoran.entity.MenuItem;
import java16.restoran.entity.User;
import java16.restoran.exceptions.NotFount;
import java16.restoran.repo.ChequeRepo;
import java16.restoran.repo.MenuItemRepo;
import java16.restoran.repo.UserRepo;
import java16.restoran.service.ChequeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ChequeServiceImpl implements ChequeService {
    private final ChequeRepo checkRepository;
    private final UserRepo userRepository;
    private final MenuItemRepo menuItemRepository;

    @Transactional
    public CheckResponse createCheck(Long waiterId, List<Long> menuItemIds) {
        User waiter = userRepository.findById(waiterId)
                .orElseThrow(() -> new NotFount("Waiter not found"));

        List<MenuItem> items = menuItemRepository.findAllById(menuItemIds);
        List<MenuResponse> menuResponses = items.stream()
                .map(MenuResponse::new)
                .collect(Collectors.toList());

        BigDecimal total = items.stream()
                .map(MenuItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal servicePercentage = BigDecimal.valueOf(waiter.getRestaurant().getService());  // 👈 Получаем процент из Restaurant
        BigDecimal serviceFee = total.multiply(servicePercentage.divide(BigDecimal.valueOf(100))); // Считаем сервис

        BigDecimal grandTotal = total.add(serviceFee);
        BigDecimal averagePrice = total.divide(BigDecimal.valueOf(items.size()), 2, BigDecimal.ROUND_HALF_UP);

        Cheque check = new Cheque(null, averagePrice, LocalDate.now().atStartOfDay(), waiter, items);

        checkRepository.save(check);

        return new CheckResponse(
                waiter.getFirstName() + " " + waiter.getLastName(),
                menuResponses,
                averagePrice,
                serviceFee,
                grandTotal
        );
    }


    @Transactional
    public void updateCheck(Long checkId, List<Long> menuItemIds) {
        Cheque check = checkRepository.findById(checkId)
                .orElseThrow(() -> new NotFount("Check not found"));

        List<MenuItem> items = menuItemRepository.findAllById(menuItemIds);
        BigDecimal total = items.stream().map(MenuItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);


        BigDecimal averagePrice = total.divide(BigDecimal.valueOf(items.size()), 2, BigDecimal.ROUND_HALF_UP);

        check.setMenuItems(items);
        check.setPriceAverage(averagePrice);

        checkRepository.save(check);
    }



    @Transactional
    public void deleteCheck(Long checkId) {
        checkRepository.deleteById(checkId);
    }

    public BigDecimal getTotalAmountByWaiter(Long waiterId) {
        return checkRepository.getTotalAmountByWaiter(waiterId).orElse(BigDecimal.ZERO);
    }

    public BigDecimal getRestaurantDailyAverage() {
        return checkRepository.getRestaurantDailyAverage().orElse(BigDecimal.ZERO);
    }
}

