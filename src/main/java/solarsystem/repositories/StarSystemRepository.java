package solarsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import solarsystem.domain.entities.StarSystem;

import java.util.List;

@Repository
public interface StarSystemRepository extends JpaRepository<StarSystem, String> {

    @Query("SELECT ss FROM StarSystem AS ss ORDER BY ss.name")
    List<StarSystem> findAllOrderByName();
}
