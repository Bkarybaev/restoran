package java16.restoran.entity;

import jakarta.persistence.*;
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
@Table(name = "sudcategoryes")
public class SubCategory {
    @Id
    @GeneratedValue(generator = "gen_sub_category", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "gen_sub_category",allocationSize = 1,initialValue = 100)
    private Long id;
    private String name;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "subCategory")
    private List<MenuItem> menuItemList = new ArrayList<>();
}
