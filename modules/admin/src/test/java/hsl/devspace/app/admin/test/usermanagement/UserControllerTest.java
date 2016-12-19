package hsl.devspace.app.admin.test.usermanagement;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@Test
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class UserControllerTest extends AbstractTestNGSpringContextTests {

    private static final Logger LOG = LogManager.getLogger(UserControllerTest.class);

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Test
    public void addUserTest()  {
        try {

            mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
            mockMvc.perform(get("/users/add"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("/user_management/userAdd"));
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("the exception is :",e);
        }
    }





}
