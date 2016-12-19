package hsl.devspace.app.admin.test.usermanagement;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@Test
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring-test-config.xml",  "classpath:testng.xml"})
public class UserControllerTest extends AbstractTestNGSpringContextTests {

    private static final Logger LOG = LogManager.getLogger(UserControllerTest.class);

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Test
    public void addUserTest()  {

        try {
            LOG.info("In Test method -- WAC = "+this.webApplicationContext);
            this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
            this.mockMvc.perform(get("https://localhost:8443/admin/users/add"))
                    .andExpect(status().is(404))
                    .andExpect(view().name("/user_management/userAdd"));
            // .andExpect(forwardedUrl("/item_management/viewItem"));
        } catch (Exception e) {
            LOG.error("error in item test1 {}", e);
        }
    }





}
