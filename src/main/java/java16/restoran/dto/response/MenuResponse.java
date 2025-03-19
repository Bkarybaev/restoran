package java16.restoran.dto.response;

import java16.restoran.entity.MenuItem;
import lombok.*;

import java.math.BigDecimal;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuResponse {
    private BigDecimal price;
    private Boolean isVegetarian;
    private String name;
    private String description;
    private String image;
    private Long id;

    public MenuResponse(MenuItem menuItem) {
        this.price = menuItem.getPrice();
        this.name = menuItem.getName();
        this.description = menuItem.getDescription();
        this.image = menuItem.getImage();
        this.isVegetarian = menuItem.getIsVegetarian();
        this.id = menuItem.getId();
    }
}
