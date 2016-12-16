package hsl.devspace.app.admin.test.usermanagement;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.Test;

@Test
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring-test-config.xml","classpath:../webapp/WEB-INF/admin-servlet.xml"})
public class UserControllerTest {

    private static final Logger LOG = LoggerFactory.getLogger(UserControllerTest.class);

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;


    @Test
    public void addUserTest() {
        try {
           this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
       /*     mockMvc.perform(get("/users/add"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("/user_management/userAdd"));*/
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("the exception is :",e);
        }
    }





}
