package java16.restoran.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cheques")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cheque {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_cheque")
    @SequenceGenerator(name = "gen_cheque", allocationSize = 1,initialValue = 100)
    private Long id;
    private BigDecimal priceAverage;
    private LocalDateTime createdAt;


    //relationships

    @ManyToOne
    private User chequeUser;

    @ManyToMany
    private List<MenuItem> menuItems = new ArrayList<>();
}
