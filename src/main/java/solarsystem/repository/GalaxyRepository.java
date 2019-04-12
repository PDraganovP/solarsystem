package solarsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import solarsystem.domain.entities.Galaxy;

import java.util.List;

@Repository
public interface GalaxyRepository extends JpaRepository<Galaxy, String> {

    @Query("SELECT g FROM Galaxy AS g ORDER BY g.name")
    List<Galaxy> findAllOrderByName();
}
