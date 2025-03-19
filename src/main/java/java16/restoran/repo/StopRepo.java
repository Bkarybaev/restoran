package java16.restoran.repo;

import java16.restoran.entity.MenuItem;
import java16.restoran.entity.StopList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.*;

public interface StopRepo extends JpaRepository<StopList,Long> {
    @Query("select s.menuItem from StopList s where s.menuItem.id = :id")
    MenuItem findByIdMenuItem(Long id);
}
