package java16.restoran.entity;

import jakarta.persistence.*;
import java16.restoran.enums.RoleUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(generator = "gen_user", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "gen_user", allocationSize = 1, initialValue = 100)
    private Long id;

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String password;

    private String email;
    private int experience;
    private String phone;
    @Enumerated(EnumType.STRING)
    private RoleUser role;

    //relationship
    @ManyToOne
    private Restaurant restaurant;



    @OneToMany(mappedBy = "chequeUser")
    private List<Cheque> chequeList = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
