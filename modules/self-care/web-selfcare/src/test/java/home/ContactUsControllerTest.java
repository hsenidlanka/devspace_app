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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Test
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
public class ContactUsControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeMethod
    public void setWac(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getViewTest() throws Exception {
        this.mockMvc.perform(get("/contact-us")
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(view().name("/home/contact-us"));
    }


    @DataProvider
    public Object[][] getDataForContactUsFail() {
        return new Object[][]{{"", "", "", "five", "1232", " "},
                {" 1232211", " 1232211", " 1232211",  "sixcddmail.xom", "sdjhbsfjs", "  fff   "},
                {"     ", "     ", "     ", " ", "00000000000", " dfs sdfdf sfs"},
                {" dsfdsf ", " dsfdsf ", " dsfdsf ", "1232424", "123456789123" ," jd ss"},
                {"    313 hgfhshjaff  fahfasdhf   ", "    313 hgfhshjaff  fahfasdhf   ", "    313 hgfhshjaff  fahfasdhf   ", "sixcddma212131312il.xom", "dwsau5425621", " jgdfh "}};

    }
    @Test(dataProvider="getDataForContactUsFail")
    public void testContactUsFailed(String inquiryType, String title, String name,String contactEmail, String contactNo, String msg) throws Exception {
        this.mockMvc.perform(post("/contact-us")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("inquiryType", inquiryType)
                .param("title", title)
                .param("name", name)
                .param("contactEmail", contactEmail)
                .param("contactNo", contactNo)
                .param("msg", msg))
                .andExpect(model().attributeHasFieldErrors("contactus", "inquiryType"))
                .andExpect(model().attributeHasFieldErrors("contactus", "title"))
                .andExpect(model().attributeHasFieldErrors("contactus", "name"))
                .andExpect(model().attributeHasFieldErrors("contactus", "contactEmail"))
                .andExpect(model().attributeHasFieldErrors("contactus", "contactNo"))
                .andExpect(model().attributeHasFieldErrors("contactus", "msg"))
                .andExpect(view().name("/home/contact-us"));
    }



    @DataProvider
    public Object[][] getDataForContactUsSuccess() {
        return new Object[][]{{"Complaint", "Mr", "Harry Potter", "jsdjdjk@fjjd.com", "0123456789", "mockMvcTest"},
                {"Suggestion", "Mrs", "Jack Frost",  "email@mail.com", "0987654321", "mockMvcTest"},
                {"Clarification", "Miss", "Santa Clause", "123menai@gmail.com", "0654123789", "mockMvcTest"},
                {"Clarification", "Rev", "Ron Wisley", "dhjdkd1234@yahoo.com", "0357159824", "mockMvcTest"},
                {"Other", "Dr", "Altaire Creed", "djdjdkdkd@jdchdhd.com", "0654852159" ,"mockMvcTest"}
        };

    }
    @Test(dataProvider="getDataForContactUsSuccess")
    public void testContactUsSuccess(String inquiryType, String title, String name,String contactEmail, String contactNo, String msg) throws Exception {
        this.mockMvc.perform(post("/contact-us")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("inquiryType", inquiryType)
                .param("title", title)
                .param("name", name)
                .param("contactEmail", contactEmail)
                .param("contactNo", contactNo)
                .param("msg", msg))
                .andExpect(redirectedUrl("/contact-us"));
    }
}