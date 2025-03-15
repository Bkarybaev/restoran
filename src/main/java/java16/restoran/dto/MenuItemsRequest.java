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



}
