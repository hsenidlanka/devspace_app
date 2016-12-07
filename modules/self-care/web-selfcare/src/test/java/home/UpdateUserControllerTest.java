package home;


import hsenid.web.Controllers.user.UpdateUserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test
@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
public class UpdateUserControllerTest extends AbstractTestNGSpringContextTests {

    UpdateUserController updateUserController = new UpdateUserController();

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeTest
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).alwaysExpect(status().isO)
    }

    @Test()
    public void testUpdateUser(){
        this.mockMvc.perform(get("/")).andExpect();
    }
}