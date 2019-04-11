package solarsystem.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import solarsystem.domain.entities.StarSystem;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StarSystemsRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private StarSystemRepository starSystemRepository;

    @Test
    public void testfindAllOrderByName() {
        StarSystem starSystemOne = new StarSystem();
        starSystemOne.setName("NDR-114");

        StarSystem starSystemTwo = new StarSystem();
        starSystemTwo.setName("Solar System");

        StarSystem starSystemThree = new StarSystem();
        starSystemThree.setName("D-503");

        entityManager.persist(starSystemOne);
        entityManager.persist(starSystemTwo);
        entityManager.persist(starSystemThree);

        List<StarSystem> starSystemsOrderByName = starSystemRepository.findAllOrderByName();
        String starSystemName = starSystemsOrderByName.get(0).getName();
        assertEquals("D-503", starSystemName);
    }
}
