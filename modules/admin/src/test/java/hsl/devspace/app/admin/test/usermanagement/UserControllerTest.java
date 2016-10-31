package hsl.devspace.app.admin.test.usermanagement;

import hsl.devspace.app.corelogic.repository.user.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by hsenid on 9/21/16.
 */
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
@WebAppConfiguration //ensures that the application context loaded for our tests is a WebApplicationContext
public class UserControllerTest  {

    private static final Logger LOG = LogManager.getLogger(UserControllerTest.class);
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    public UserRepository staffRepository;

    @Autowired
    public WebApplicationContext webApplicationContext;

    //resets the service mock before each test and create a new MOckMvc
    @Before
    public void setup(){
        Mockito.reset(staffRepository);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testUserAddForm() throws Exception{
       mockMvc.perform(get("/add"))
               .andExpect(status().isOk())
               .andExpect(view().name("user_management/userAdd"))
               .andExpect(forwardedUrl("WEB-INF/views/user_management/userAdd.jsp"));
//       assertTrue(true);
    }



}
