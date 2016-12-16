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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Test
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
public class UpdateUserControllerTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @DataProvider
    public Object[][] usernamesForUserDataSend() {
        return new Object[][]{{"testre"},
                {"kkalla"},
                {"block"},
                {"tagtest"}
        };

    }


    @Test(dataProvider = "usernamesForUserDataSend")
    public void testUpdateUserDataSuccess(String username) throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        this.mockMvc.perform(get("/sendUserData")
                .param("username", username)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(username)));
    }

    @DataProvider
    public Object[][] usernamesForNotUsers() {
        return new Object[][]{{"notauser"},
                {"3872t8732823879383879"},
                {"%#$%&&%$%&&*(())]"},
                {"dgdhs1728*&^%$#"},
                {"     "}};

    }
    @Test(dataProvider = "usernamesForNotUsers")
    public void testUpdateUserDataFailed(String notUsernam) throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        this.mockMvc.perform(get("/sendUserData")
                .param("username", notUsernam)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is((Object) null)));
    }

    @DataProvider
    public Object[][] getDataForUpdateUserFail() {
        return new Object[][]{{"", "", "", "five", "1232"},
                {" 1232211", " 1232211", " 1232211",  "sixcddmail.xom", "sdjhbsfjs"},
                {"     ", "     ", "     ", " ", "00000000000"},
                {" dsfdsf ", " dsfdsf ", " dsfdsf ", "1232424", "123456789123"},
                {"    313 hgfhshjaff  fahfasdhf   ", "    313 hgfhshjaff  fahfasdhf   ", "    313 hgfhshjaff  fahfasdhf   ", "sixcddma212131312il.xom", "dwsau5425621"}};

    }


    @Test(dataProvider="getDataForUpdateUserFail")
    public void testUpdateUserValidationFail(String title, String firstName, String lastName,String email, String mobile) throws Exception {
        this.mockMvc.perform(post("/update-user")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", title)
                .param("firstName", firstName)
                .param("lastName", lastName)
                .param("username", "kkalla")
                .param("email", email)
                .param("addressL01", "addr1")
                .param("addressL02", "addr2")
                .param("addressL03", "addr3")
                .param("mobile", mobile))
                .andExpect(model().attributeHasFieldErrors("updateuser", "title"))
                .andExpect(model().attributeHasFieldErrors("updateuser", "firstName"))
                .andExpect(model().attributeHasFieldErrors("updateuser", "lastName"))
                .andExpect(model().attributeHasFieldErrors("updateuser", "email"))
                .andExpect(model().attributeHasFieldErrors("updateuser", "mobile"))
                .andExpect(view().name("/home/update-user"));

    }

    @DataProvider
    public Object[][] getDataForUpdateUserSuccess() {
        return new Object[][]{
                {"Mr", "First", "Last", "first@gmail.com", "addr1","addr2", "0123456789"},
                {"Mrs", "Jack", "Ethan","jack@mail.com", "ward", "Reid", "0412356789"},
                {"Miss", "Sparrow", "Bourno","sparrow@email.com", "place", "Avenue", "0714562378"},
                {"Rev", "Harry", "Tinman","harry@yahoo.com", "town", "UCSC", "0774562318"},
                {"Dr", "Potter", "Robert","potter@hotmail.com", "hall", "Complex", "0112345678"}
        };
    }

    @Test(dataProvider="getDataForUpdateUserSuccess")
    public void testUpdateUserValidationSuccess(String title, String firstName, String lastName, String email, String addr1, String addr2, String mobile) throws Exception {
        this.mockMvc.perform(post("/update-user")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", title)
                .param("firstName", firstName)
                .param("lastName", lastName)
                .param("username", "kkalla")
                .param("email", email)
                .param("addressL01", addr1)
                .param("addressL02", addr2)
                .param("addressL03", "addr3")
                .param("mobile", mobile))
                .andExpect(redirectedUrl("/update-user"))
        ;
    }

}