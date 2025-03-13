package java16.restoran.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_food")
    @SequenceGenerator(
            name = "gen_food",
            sequenceName = "seq_food",
            allocationSize = 1,
            initialValue = 100)
    private Long id;

    private String name;
}
