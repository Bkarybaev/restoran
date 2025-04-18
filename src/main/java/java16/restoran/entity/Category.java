package java16.restoran.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categoryes")
public class Category {
    @Id
    @GeneratedValue(generator = "gen_category", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "gen_category", allocationSize = 1,initialValue = 100)
    private Long id;

    @NotBlank
    private String name;

    //relationships
    @OneToMany(mappedBy = "category",cascade = CascadeType.REMOVE)
    private List<SubCategory> subCategories = new ArrayList<>();
}

