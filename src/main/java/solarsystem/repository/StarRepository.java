package solarsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import solarsystem.domain.entities.Star;

import java.util.List;

@Repository
public interface StarRepository extends JpaRepository<Star, String> {

    @Query("SELECT s FROM Star AS s ORDER BY s.name")
    List<Star> findAllOrderByName();
}
