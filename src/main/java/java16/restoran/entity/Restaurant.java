package java16.restoran.entity;

import jakarta.persistence.*;
import java16.restoran.enums.RestaurantType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(generator = "gen_restaurant", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "gen_restaurant" , allocationSize = 1,initialValue = 100)
    private Long id;

    private String name;
    private String location;
    @Enumerated(EnumType.STRING)
    private RestaurantType restType;
    private Integer numberOrEmployees;
    private Integer service;

    //relationships

    @OneToMany(mappedBy = "restaurant")
    private List<User> users= new ArrayList<>();

    @OneToMany(mappedBy = "restaurant")
    private List<MenuItem> menuItems= new ArrayList<>();

}
