package java16.restoran.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantResponse {
    private String name;
    private String location;
    private String resType;
    private int numberOfPeople;
    private int service;
    private String description;
}
