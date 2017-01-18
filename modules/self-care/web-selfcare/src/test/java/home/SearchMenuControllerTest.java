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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@Test
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
public class SearchMenuControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeMethod
    public void setWac(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getSearchViewTest() throws Exception {
        this.mockMvc.perform(get("/search-menu")
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(view().name("/home/search-menu"));
    }

    @DataProvider
    public Object[][] dataForSearchMenuUrl() {
        return new Object[][]
                {
                        {"test", "Test"},
                        {"cheese", "Pizza"},
                        {"barbercue", "Beverage"},
                        {"hulk", "dfgfdg"},
                        {"3", "4"},
                        {"42134ghkjhj", "fgdfh34324h3"},
                        {"12345", "67890"}
                };
    }

    @Test(dataProvider = "dataForSearchMenuUrl")
    public void getPathVariableViews(String searchItem, String category) throws Exception {
        this.mockMvc.perform(get("/menu/{searchItem}/{category}", searchItem, category))
                .andExpect(status().isOk())
                .andExpect(view().name("search-results"));
    }
}