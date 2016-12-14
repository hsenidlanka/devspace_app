package home;

import hsenid.config.WebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Test
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
public class LoginControllerTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Test
    public void testCheckNotBlockedUser() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        this.mockMvc.perform(get("/chechBlocked")
                .param("checkName", "testre")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'userAvailable':true}"));
    }

    @Test
    public void testCheckBlockedUser() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        this.mockMvc.perform(get("/chechBlocked")
                .param("checkName", "block")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'userAvailable':false}"));
    }

    @DataProvider
    public Object[][] loginDetails() {
        return new Object[][]{
                {"testre", "password"},
                {"kkalla", "password"}
        };
    }

    @Test(dataProvider="loginDetails")
    public void testSuccessLogin(String username, String password) throws Exception {
        this.mockMvc.perform(get("/login")
                .param("username", username)
                .param("password", password)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userAvailable", is(true)));
    }

    @DataProvider
    public Object[][] loginDetailsInvalid() {
        return new Object[][]{
                {"testre", ""},
                {"kkalla", "passworddd"},
                {"", "password"},
                {"kkalladd", "password"},
                {"testre3f", "password"},
                {"kkalla123", "password"},
                {"1233", "jvjvbvjba"},
                {"", ""},
                {"kkalla", ""}
        };
    }

    @Test(dataProvider="loginDetailsInvalid")
    public void testFailedLogin(String username, String password) throws Exception {
        this.mockMvc.perform(get("/login")
                .param("username", username)
                .param("password", password)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userAvailable", is(false)));
    }



}