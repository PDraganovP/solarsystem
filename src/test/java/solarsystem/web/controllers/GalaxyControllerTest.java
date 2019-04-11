package solarsystem.web.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import solarsystem.SolarSystemApplication;
import solarsystem.repository.GalaxyRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SolarSystemApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class GalaxyControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private GalaxyRepository galaxyRepository;

    @Test
    public void testShow() throws Exception {
        this.mvc.perform(get("/galaxies/show")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("galaxies/all-galaxies"));

    }
    @Test
    public void testRenderAddGalaxyPage() throws Exception {
        this.mvc.perform(get("/galaxies/add")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("galaxies/add-galaxy"));

    }
    @Test
    public void testRenderEditGalaxyPage() throws Exception {
        this.mvc.perform(get("/galaxies/edit")).andDo(print()).andExpect(status().isNotFound());

    }
}
