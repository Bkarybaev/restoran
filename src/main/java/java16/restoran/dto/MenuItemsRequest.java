package java16.restoran.dto;

import java16.restoran.entity.MenuItem;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
public class MenuItemsRequest {
    private BigDecimal price;
    private Boolean isVegetarian;
    private String name;
    private String description;
//    private Long categoryId;
//    private Long subcategoryId;

    public MenuItem newMenuItem(){
        MenuItem menuItem = new MenuItem();
        menuItem.setName(name);
        menuItem.setDescription(description);
        menuItem.setPrice(price);
        menuItem.setIsVegetarian(isVegetarian);
        return menuItem;
    }

}
