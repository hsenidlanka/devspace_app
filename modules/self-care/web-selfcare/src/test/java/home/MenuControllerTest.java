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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Test
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
public class MenuControllerTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeMethod
    public void setWac(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getMenuView() throws Exception {

        this.mockMvc.perform(get("/menu")
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("categories"))
                .andExpect(status().isOk())
                .andExpect(view().name("home/menu"));
    }

//    Use dataproviders. Further test for Content
    @Test
    public void getPathVariableViews() throws Exception {
        this.mockMvc.perform(get("/menu/{category}", 3))
                .andExpect(status().isOk());
    }

    @Test
    public void getToppingTest() throws Exception {
        this.mockMvc.perform(get("/menu/toppings"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @DataProvider
    public Object[][] TestMenuCategoryUrl() {
        return new Object[][]
                {
                        {"test"},
                        {"cheese"},
                        {"barbercue"},
                        {"hulk"},
                        {"3"},
                        {"42134ghkjhj"},
                        {"12345"}
                };
    }

    @Test(dataProvider = "TestMenuCategoryUrl")
    public void getPathVariableViews(String category) throws Exception {
        this.mockMvc.perform(get("/menu/{category}", category))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("category"))
                .andExpect(model().attributeExists("itemImageUrl"))
                .andExpect(model().attributeExists("subcategories"))
                .andExpect(view().name("home/menu-category"));
    }
}