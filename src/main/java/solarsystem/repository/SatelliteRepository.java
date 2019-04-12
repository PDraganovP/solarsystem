package solarsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import solarsystem.domain.entities.Satellite;

import java.util.List;

@Repository
public interface SatelliteRepository extends JpaRepository<Satellite, String> {
    @Query("SELECT s FROM Satellite AS s ORDER BY s.name")
    List<Satellite> findAllOrderByName();
}
