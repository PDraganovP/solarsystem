package solarsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import solarsystem.domain.entities.Planet;

import java.util.List;


@Repository
public interface PlanetRepository extends JpaRepository<Planet, String> {

    @Query("SELECT p FROM Planet AS p ORDER BY p.name")
    List<Planet> findAllOrderByName();
}
