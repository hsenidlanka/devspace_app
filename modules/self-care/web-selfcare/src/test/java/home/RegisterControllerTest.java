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

import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Test
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
public class RegisterControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Test(dataProvider = "usernamesForRegisterFail")
    public void testUniqueUsernameAlreadyIn(String alreadyUser) throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        this.mockMvc.perform(get("/UniqueUser")
                .param("checkName", alreadyUser)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'userAvailable':false}"));
    }


    @DataProvider
    public Object[][] usernamesForUniqueTest() {
        return new Object[][]{{"notauser"},
                {"3872t8732823879383879"},
                {"%#$%&&%$%&&*(())]"},
                {"dgdhs1728*&^%$#"},
                {"     "}};

    }

    @Test(dataProvider = "usernamesForUniqueTest")
    public void testUniqueUsernameNotIn(String notYetUsername) throws Exception {
        this.mockMvc.perform(get("/UniqueUser")
                .param("checkName", notYetUsername)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'userAvailable':true}"));
    }

    @Test
    public void testRegisterSuccess() throws Exception {
        Random random = new Random();
        int userInt = random.nextInt(100000) + 1;
        this.mockMvc.perform(post("/register")
                .param("title", "Mr")
                .param("fname", "mockTest")
                .param("lname", "mockTest")
                .param("address1", "mockTest")
                .param("address2", "mockTest")
                .param("address3", "mockTest")
                .param("mobileNo", "1010101010")
                .param("email", "mockTest@gmail.com")
                .param("password", "password")
                .param("uname", String.valueOf(userInt))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'userAvailable':true}"));
    }


    @DataProvider
    public Object[][] usernamesForRegisterFail() {
        return new Object[][]{{"testre"},
                {"kkalla"},
                {"block"},
                {"tagtest"}
        };
    }

    @Test(dataProvider = "usernamesForRegisterFail")
    public void testRegisterFailedUsername(String alreadyUsername) throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        this.mockMvc.perform(post("/register")
                .param("title", "Mr")
                .param("fname", "mockTest")
                .param("lname", "mockTest")
                .param("address1", "mockTest")
                .param("address2", "mockTest")
                .param("address3", "mockTest")
                .param("mobileNo", "1010101010")
                .param("email", "mockTest@gmail.com")
                .param("password", "password")
                .param("uname", alreadyUsername)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'userAvailable':false}"));
    }


}