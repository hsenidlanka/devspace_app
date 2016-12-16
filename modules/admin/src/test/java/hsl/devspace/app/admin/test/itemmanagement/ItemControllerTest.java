package hsl.devspace.app.admin.test.itemmanagement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(locations = {"classpath:../webapp/WEB-INF/admin-servlet.xml",
                                   "classpath:spring-test-config.xml"})

@WebAppConfiguration //ensures that the application context loaded for our tests is a WebApplicationContext
public class ItemControllerTest {

    private static final Logger LOGGER = LogManager.getLogger(ItemControllerTest.class);

   // @SuppressWarnings("SpringJavaAutowiringInspection")

    @Autowired
    public WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        LOGGER.trace("invoking item test1");
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testItemPage() throws Exception {
        try {
            this.mockMvc.perform(get("/items/view"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("/WEB-INF/views/item_management/viewItem.jsp"))
                    .andExpect(forwardedUrl("/item_management/viewItem"));
        } catch (Exception e) {
            LOGGER.error("error in item test1 {}", e);
        }

    }


}
