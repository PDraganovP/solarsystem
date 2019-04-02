package solarsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import solarsystem.domain.entities.StarSystem;

@Repository
public interface StarSystemRepository extends JpaRepository<StarSystem, String> {
}
