package java16.restoran.dto.response;

import java16.restoran.entity.MenuItem;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Getter @Setter
@NoArgsConstructor
public class CheckResponse {
    private String waiterFullName;
    private List<MenuResponse> items;
    private BigDecimal averagePrice;
    private BigDecimal serviceFee;
    private BigDecimal grandTotal;

    public CheckResponse(String waiterFullName, List<MenuResponse> items, BigDecimal averagePrice, BigDecimal serviceFee, BigDecimal grandTotal) {
        this.waiterFullName = waiterFullName;
        this.items = items;
        this.averagePrice = averagePrice;
        this.serviceFee = serviceFee;
        this.grandTotal = grandTotal;
    }
}
