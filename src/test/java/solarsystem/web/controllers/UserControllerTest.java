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
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    // @WithMockUser(roles = "USER")
    public void testRegister() throws Exception {
        this.mvc.perform(get("/users/register")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("users/register"));

    }

    @Test
    // @WithMockUser(roles = "USER")
    public void testLogin() throws Exception {
        this.mvc.perform(get("/users/login")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("users/login"));

    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testAllUsers() throws Exception {
        this.mvc.perform(get("/users/all")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("users/all-users"));

    }
}
