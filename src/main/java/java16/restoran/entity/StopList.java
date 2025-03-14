package java16.restoran.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stop_lists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StopList {
    @Id
    @GeneratedValue(generator = "gen_stoplist", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "gen_stoplist" , allocationSize = 1,initialValue = 100)
    private Long id;

    private String reason;
    private LocalDateTime dateTime;

    @OneToOne
    private MenuItem menuItem;

    @PrePersist @PreUpdate
    private void prePersist() {
        dateTime = LocalDateTime.now();
    }
}
