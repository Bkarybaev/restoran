package java16.restoran.api;

import java16.restoran.dto.response.CheckResponse;
import java16.restoran.service.ChequeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/checks")
@RequiredArgsConstructor
public class ChequeApi {
    private final ChequeService checkService;

    // Чек түзүү (Официант жана Админ жасай алат)
    @PostMapping("/create")
    @PreAuthorize("hasRole('WAITER') or hasRole('ADMIN')")
    public ResponseEntity<CheckResponse> createCheck(@RequestParam Long waiterId, @RequestParam List<Long> menuItemIds) {
        return ResponseEntity.ok(checkService.createCheck(waiterId, menuItemIds));
    }

    // Чекти жаңыртуу (Только Админ)
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateCheck(@PathVariable Long id, @RequestParam List<Long> menuItemIds) {
        checkService.updateCheck(id, menuItemIds);
        return ResponseEntity.ok("Check updated successfully.");
    }

    // Чекти өчүрүү (Только Админ)
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCheck(@PathVariable Long id) {
        checkService.deleteCheck(id);
        return ResponseEntity.ok("Check deleted successfully.");
    }

    // Официанттын бир күндүк чектеринин жалпы суммасын алуу
    @GetMapping("/waiter-total/{waiterId}")
    @PreAuthorize("hasRole('WAITER') or hasRole('ADMIN')")
    public ResponseEntity<BigDecimal> getWaiterTotal(@PathVariable Long waiterId) {
        return ResponseEntity.ok(checkService.getTotalAmountByWaiter(waiterId));
    }

    // Ресторандын бир күндүк орточо чек суммасын алуу (Только Админ)
    @GetMapping("/restaurant-daily-average")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BigDecimal> getRestaurantDailyAverage() {
        return ResponseEntity.ok(checkService.getRestaurantDailyAverage());
    }
}
