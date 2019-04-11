package solarsystem.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import solarsystem.domain.entities.Planet;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PlanetRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private PlanetRepository planetRepository;

    @Test
    public void testfindAllOrderByName() {
        Planet planetOne = new Planet();
        planetOne.setName("Venus");

        Planet planetTwo = new Planet();
        planetTwo.setName("Saturn");

        Planet planetThree = new Planet();
        planetThree.setName("Mars");

        entityManager.persist(planetOne);
        entityManager.persist(planetTwo);
        entityManager.persist(planetThree);

        List<Planet> planetsOrderByName = planetRepository.findAllOrderByName();
        String planetName = planetsOrderByName.get(0).getName();
        assertEquals("Mars", planetName);
    }
}
