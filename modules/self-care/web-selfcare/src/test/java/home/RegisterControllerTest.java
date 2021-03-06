package home;

import hsenid.config.WebConfig;
import hsenid.web.supportclasses.SendStringBuilds;
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

import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Test
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
public class RegisterControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeMethod
    public void setWac(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void registrationGetTest() throws Exception {
        this.mockMvc.perform(get("/register")
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(view().name("home/register"));
    }

    @DataProvider
    public Object[][] getDataForRegisterFail() {
        return new Object[][]{{"", "", "", "", "", "", "", "", ""},
                {"", " 1232211", " 1232211",  "sixcddmail.xom", "sdjhbsfjs", "  fff   ", "", "", ""},
                {"", "     ", "     ", " ", "00000000000", " dfs sdfdf sfs", "", "", ""},
                {"", " dsfdsf ", " dsfdsf ", "1232424", "123456789123" ," jd ss", "", "", ""},
                {"", "    313 hgfhshjaff  fahfasdhf   ", "    313 hgfhshjaff  fahfasdhf   ", "sixcddma212131312il.xom", "dwsau5425621", " jgdfh ", "", "", ""}};

    }

    @Test(dataProvider="getDataForRegisterFail")
    public void testRegisterFailed(String title, String fName, String lName, String email, String uname, String mobile, String addr1, String addr2, String addr3) throws Exception {

        this.mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", title)
                .param("firstName", fName)
                .param("lastName", lName)
                .param("email", email)
                .param("username", uname)
                .param("mobile", mobile)
                .param("addressLine01", addr1)
                .param("addressLine02", addr2)
                .param("addressLine03", addr3))
                .andExpect(model().attributeHasFieldErrors("register", "title"))
                .andExpect(model().attributeHasFieldErrors("register", "firstName"))
                .andExpect(model().attributeHasFieldErrors("register", "lastName"))
                .andExpect(model().attributeHasFieldErrors("register", "email"))
                .andExpect(model().attributeHasFieldErrors("register", "mobile"))
                .andExpect(model().attributeHasFieldErrors("register", "addressLine01"))
                .andExpect(model().attributeHasFieldErrors("register", "addressLine02"))
                .andExpect(view().name("/home/register"));
    }



    @DataProvider
    public Object[][] getDataForRegisterSuccess() {
        return new Object[][]{
                {"Mr", "Captain", "Sparrow", "Cap@mail.com", "0412269472", "Meth", "Niwasa", "Gandara"},
                {"Mrs", "John", "Carter",  "johnacarter@yahoo.com", "0712341234", "Test", "Lane", ""},
                {"Miss", "Harry", "Potter", "harry@howarts.edu", "0115829349", "WTC", "Pokiz balle", " "},
                {"Dr", "Tom", "Sawyer", "tomsawyer@mark.twein", "0942281828" ,"Central Lane", "Siri Mawatha", "Colombo"},
                {"Rev", "Huck", "Finn", "huckfinn@mark.twein", "0547281810", "Ape Gama", "Gandara ", "Matara "}};

    }

    @Test(dataProvider="getDataForRegisterSuccess")
    public void testRegisterSuccess(String title, String fName, String lName, String email, String mobile, String addr1, String addr2, String addr3) throws Exception {

        Random random = new Random();
        int userInt = random.nextInt(100000) + 1;

        String redirectUrl = SendStringBuilds.sendString("/email-verification?username=", String.valueOf(userInt));
        this.mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", title)
                .param("firstName", fName)
                .param("lastName", lName)
                .param("email", email)
                .param("username", String.valueOf(userInt))
                .param("password", "password")
                .param("mobile", mobile)
                .param("addressLine01", addr1)
                .param("addressLine02", addr2)
                .param("addressLine03", addr3))
                .andExpect(redirectedUrl(redirectUrl));
    }

}