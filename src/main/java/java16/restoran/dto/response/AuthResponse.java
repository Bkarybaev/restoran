package java16.restoran.dto.response;

import java16.restoran.enums.RoleUser;
import lombok.Builder;

@Builder
public record AuthResponse(
        String token,
        String email,
        RoleUser role
) {
}
