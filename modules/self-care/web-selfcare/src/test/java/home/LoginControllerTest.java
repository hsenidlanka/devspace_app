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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Test
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
public class LoginControllerTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeMethod
    public void setWac(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void blockedUserTest() throws Exception {
        this.mockMvc.perform(get("/blockedUserRedirect")
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(view().name("/error-pages/error500"));
    }


    @DataProvider
    public Object[][] notBlockedUsername() {
        return new Object[][]{
                {"testre"},
                {"admin"}
        };
    }

    @Test(dataProvider = "notBlockedUsername")
    public void resendVerifyTestSuccess(String username) throws Exception {
        this.mockMvc.perform(post("/resendVerification")
                .param("username", username)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userAvailable").value(true));
    }


    @DataProvider
    public Object[][] loginDetails() {
        return new Object[][]{
                {"testre", "password"}
        };
    }

    @Test(dataProvider="loginDetails")
    public void testSuccessLogin(String username, String password) throws Exception {
        this.mockMvc.perform(post("/login")
                .param("username", username)
                .param("password", password)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userStatus").value("verified"));
    }

    @DataProvider
    public Object[][] loginDetailsInvalid() {
        return new Object[][]{
                {"testre", " "},
                {"kkalla", "passworddd"},
                {" ", "password"},
                {"kkalladd", "password"},
                {"testre3f", "password"},
                {"kkalla123", "password"},
                {"1233", "jvjvbvjba"},
                {" ", " "},
                {"kkalla", " "}
        };
    }

    @Test(dataProvider="loginDetailsInvalid")
    public void testFailedLogin(String username, String password) throws Exception {
        this.mockMvc.perform(post("/login")
                .param("username", username)
                .param("password", password)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userStatus").value("unauthorized"));
    }


}