package solarsystem.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import solarsystem.domain.entities.Galaxy;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GalaxyRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private GalaxyRepository galaxyRepository;

    @Test
    public void testfindAllOrderByName() {
        Galaxy galaxyOne = new Galaxy();
        galaxyOne.setName("Andromeda");

        Galaxy galaxyTwo = new Galaxy();
        galaxyTwo.setName("MilkyWay");

        Galaxy galaxyThree = new Galaxy();
        galaxyThree.setName("AlphaOne");

        entityManager.persist(galaxyOne);
        entityManager.persist(galaxyTwo);
        entityManager.persist(galaxyThree);

        List<Galaxy> galaxiesOrderByName = galaxyRepository.findAllOrderByName();
        String galaxyName = galaxiesOrderByName.get(0).getName();
        assertEquals("AlphaOne", galaxyName);
    }

}
