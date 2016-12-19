package hsl.devspace.app.admin.test.itemmanagement;

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
@ContextConfiguration(locations = {"classpath:spring-test-config.xml","classpath:testng.xml"})
@WebAppConfiguration()
public class ItemControllerTest extends AbstractTestNGSpringContextTests {

    private static final Logger LOGGER = LogManager.getLogger(ItemControllerTest.class);

    @Autowired
    public WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;


  /*  @BeforeClass(dependsOnMethods={"springTestContextPrepareTestInstance"})
    public void setupParamValidation(){
        // Test class setup code with autowired classes goes here
    }*/

    @Test
    public void testItemPage() {
       try {
           this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
            this.mockMvc.perform(get("/items/view"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("/item_management/viewItem"));
                   // .andExpect(forwardedUrl("/item_management/viewItem"));
        } catch (Exception e) {
            LOGGER.error("error in item test1 {}", e);
       }
    }



    /*@Test
    public void testItemAdd() {
        try {
            this.mockMvc.perform(get("/items/add"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("WEB-INF/views/item_management/addItem.jsp"))
                    .andExpect(forwardedUrl("/item_management/add"));
        } catch (Exception e) {
            LOGGER.error("error in add item test1 {}", e);
        }
    }*/

}
