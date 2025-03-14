package java16.restoran.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menuitems")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_food")
    @SequenceGenerator(
            name = "gen_food",
            sequenceName = "seq_food",
            allocationSize = 1,
            initialValue = 100)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
    private Boolean isVegetarian;
    private String image;

    //relationships
    @ManyToOne
    private User restouranUser;

    @ManyToMany(mappedBy = "menuItems")
    private List<Cheque> chequeList= new ArrayList<>();

    @OneToOne
    private User stopListUser;

    @ManyToOne
    private SubCategory subCategory;

    @ManyToOne
    private Restaurant restaurant;




}
