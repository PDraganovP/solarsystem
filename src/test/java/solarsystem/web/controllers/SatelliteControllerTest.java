package solarsystem.web.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import solarsystem.SolarSystemApplication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SolarSystemApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SatelliteControllerTest {
    @Autowired
    private MockMvc mvc;


    @Test
    @WithMockUser(roles = "USER")
    public void testShow() throws Exception {
        this.mvc.perform(get("/satellites/show")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("satellites/all-satellites"));

    }

    @Test
    @WithMockUser(roles = "MODERATOR")
    public void testGetAddSatellitePage() throws Exception {
        this.mvc.perform(get("/satellites/add")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("satellites/add-satellite"));

    }

    @Test
    @WithMockUser(roles = "MODERATOR")
    public void testGetEditSatellitePage() throws Exception {
        this.mvc.perform(get("/satellites/edit")).andDo(print()).andExpect(status().isNotFound());
    }
    @Test
    @WithMockUser(roles = "USER")
    public void testGetCompareSatellitesPage() throws Exception {
        this.mvc.perform(get("/satellites/compareSatellites")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("satellites/compare-satellites"));
    }
}
