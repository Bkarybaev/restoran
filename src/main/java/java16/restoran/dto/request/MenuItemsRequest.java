package java16.restoran.dto.request;

import lombok.Data;

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
