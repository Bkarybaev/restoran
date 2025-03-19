package java16.restoran.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MenuItemsRequest {
    private BigDecimal price;
    private Boolean isVegetarian;
    private String name;
    private String description;
    private Long restaurantId;
    private Long subcategoryId;
}
