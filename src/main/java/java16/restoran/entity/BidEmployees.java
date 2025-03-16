package java16.restoran.entity;

import jakarta.persistence.*;
import java16.restoran.enums.RoleUser;
import jdk.dynalink.linker.LinkerServices;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BidEmployees {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "gen_bid")
    @SequenceGenerator(name = "gen_bid",allocationSize = 1,initialValue = 100)
     private Long id;

    private String firstName;
    private String email;
    private int age;
    private int experience;
    private String phone;
    private String password;
    @Enumerated(EnumType.STRING)
    private RoleUser role;
    @ManyToMany
    private List<Restaurant> restaurant = new ArrayList<>();


}
