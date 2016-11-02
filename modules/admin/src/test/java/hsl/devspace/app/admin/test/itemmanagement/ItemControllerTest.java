/*
package hsl.devspace.app.admin.test.itemmanagement;

import hsl.devspace.app.corelogic.repository.item.ItemRepository;
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

@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
@WebAppConfiguration //ensures that the application context loaded for our tests is a WebApplicationContext
public class ItemControllerTest  {

    private static final Logger LOGGER = LogManager.getLogger(ItemControllerTest.class);
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    public ItemRepository itemRepository;

    @Autowired
    public WebApplicationContext webApplicationContext;

    //resets the service mock before each test and create a new MOckMvc
    @Before
    public void setup(){
        Mockito.reset(itemRepository);
        LOGGER.trace("invoking item test1");
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testItemPage() throws Exception{
        mockMvc.perform(get("/view"))
                .andExpect(status().isOk())
                .andExpect(view().name("item_management/viewItem"))
                .andExpect(forwardedUrl("/WEB-INF/views/item_management/viewItem.jsp"));
    }



}
*/
