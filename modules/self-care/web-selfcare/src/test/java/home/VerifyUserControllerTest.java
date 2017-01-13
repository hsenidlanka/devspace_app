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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@Test
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
public class VerifyUserControllerTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    @BeforeMethod
    public void setWac(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getEmailVerificationTest() throws Exception {

        this.mockMvc.perform(get("/email-verification")
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(view().name("includes/email-verification"));
    }

    @DataProvider
    public Object[][] dataForVerifyFail() {
        return new Object[][]{
                {"", "lkgbdjkgfd"},
                {"hhhh", "Johfjgkdjfkgfdgn"},
                {"224324", "fkgskgsdkfpgofdp[gHarry"},
                {"435435", "##$$%122334"},
                {"Rev", ""},
                {"fdhjfjf", "&*(()()))"}
        };
    }

    @Test(dataProvider="dataForVerifyFail")
    public void verifyFailedTes(String uname, String verifyCode) throws Exception {

        this.mockMvc.perform(post("/email-verification")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", uname)
                .param("verificationCode", verifyCode))
                .andExpect(model().attributeHasFieldErrors("emailverification", "verificationCode"))
                .andExpect(view().name("includes/email-verification"));
    }
}