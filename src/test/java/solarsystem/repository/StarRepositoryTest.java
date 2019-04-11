package solarsystem.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import solarsystem.domain.entities.Star;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StarRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private StarRepository starRepository;

    @Test
    public void testfindAllOrderByName() {
        Star starOne = new Star();
        starOne.setName("Nemesis");

        Star starTwo = new Star();
        starTwo.setName("Sun");

        Star starThree = new Star();
        starThree.setName("AlphaCentavrois");

        entityManager.persist(starOne);
        entityManager.persist(starTwo);
        entityManager.persist(starThree);

        List<Star> starsOrderByName = starRepository.findAllOrderByName();
        String starName = starsOrderByName.get(0).getName();
        assertEquals("AlphaCentavrois", starName);
    }
}
