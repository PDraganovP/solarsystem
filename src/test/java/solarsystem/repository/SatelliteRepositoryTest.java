package solarsystem.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import solarsystem.domain.entities.Satellite;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SatelliteRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private SatelliteRepository satelliteRepository;

    @Test
    public void testfindAllOrderByName() {
        Satellite satelliteOne = new Satellite();
        satelliteOne.setName("Titan");

        Satellite satelliteTwo = new Satellite();
        satelliteTwo.setName("Fobus");

        Satellite satelliteThree = new Satellite();
        satelliteThree.setName("Moon");

        entityManager.persist(satelliteOne);
        entityManager.persist(satelliteTwo);
        entityManager.persist(satelliteThree);

        List<Satellite> satellitesOrderByName = satelliteRepository.findAllOrderByName();
        String satelliteName = satellitesOrderByName.get(0).getName();
        assertEquals("Fobus", satelliteName);
    }
}
