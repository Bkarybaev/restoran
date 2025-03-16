package java16.restoran.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleUser implements GrantedAuthority {
    ADMIN,
    CLIENT,
    WAITER,
    POVAR;

    @Override
    public String getAuthority() {
        return name();
    }
}
