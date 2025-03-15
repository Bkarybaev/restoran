package java16.restoran.dto.authrequest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterRequest {
    @NotBlank(message = "ne doljen byt pustym")
    private String fistName;
    @NotBlank(message = "ne doljen byt pustym")
    private String lastName;
    private LocalDate birthDate;
    @NotBlank(message = "ne doljen byt pustym")
    @Email(message = "email invalid")
    private String email;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}$",message = "password invalid!!")
    @NotBlank(message = "ne doljen byt pustym")
    private String password;
    @Pattern(regexp = "^\\+996(500|501|502|503|504|505|506|507|508|509|550|551|552|553|554|555|556|557|558|559|600|601|602|603|604|605|606|607|608|609|700|701|702|703|704|705|706|707|708|709|750|751|752|753|754|755|756|757|758|759)\\d{6}$",
    message = "number dolgen soderjat +996 i bolshe 6")
    @NotBlank(message = "ne doljen byt pustym")
    private String phone;
}
