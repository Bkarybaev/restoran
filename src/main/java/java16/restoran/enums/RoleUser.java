package java16.restoran.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleUser implements GrantedAuthority {
    ADMIN,
    CLIENT,
    CHEF,
    WAITER;

    @Override
    public String getAuthority() {
        return name();
    }
}
