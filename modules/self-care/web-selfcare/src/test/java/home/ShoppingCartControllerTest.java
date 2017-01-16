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
public class ShoppingCartControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeMethod
    public void setWac(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getShoppingCartTest() throws Exception {
        this.mockMvc.perform(get("/shopping-cart")
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(view().name("/home/shopping-cart"));
    }


    @Test
    public void testProceedTocheckoutZero() throws Exception {
        this.mockMvc.perform(get("/shopping-cart/proceed")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("0"));
    }

    @DataProvider
    public Object[][] usernamesForTestProceed() {
        return new Object[][]
                {
                        {"Test"},
                        {"213123"},
                        {" "},
                        {"%$^$^%^*&"},
                        {"JHBJB*&*&J786"}
                };
    }

    @Test(dataProvider = "usernamesForTestProceed")
    public void testProceedTocheckoutOne(String username) throws Exception {
        this.mockMvc.perform(get("/shopping-cart/proceed")
                .sessionAttr("username", username)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("1"));
    }



}