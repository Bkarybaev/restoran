package java16.restoran.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java16.restoran.enums.RoleUser;
import lombok.Data;

@Data
public class BidRequest {
    @NotBlank(message = "pole ne doljno byt pustym")
    private String firstName;
    @NotBlank
    @Email(message = "email invalid")
    private String email;
    private int age;
    private int experience;
    @NotBlank(message = "pole ne doljno byt pustym")
    @Pattern(regexp = "^\\+996(500|501|502|503|504|505|506|507|508|509|550|551|552|553|554|555|556|557|558|559|600|601|602|603|604|605|606|607|608|609|700|701|702|703|704|705|706|707|708|709|750|751|752|753|754|755|756|757|758|759)\\d{6}$",
            message = "number dolgen soderjat +996 i bolshe 6")
    private String phone;
    @NotBlank(message = "pole ne doljno byt pustym")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}$",message = "password invalid!!")
    private String password;
    private String workName;
}
