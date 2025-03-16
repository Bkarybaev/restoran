package java16.restoran.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantRequest {
    @NotBlank(message = "на name надо что то написать")
    private String name;
    @NotBlank(message = "на location что то написать")
    private String location;
    private String resType;
    private int numberOfPeople;
    private int service;
}
