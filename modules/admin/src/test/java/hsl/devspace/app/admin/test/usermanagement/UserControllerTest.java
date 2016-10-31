package hsl.devspace.app.admin.test.usermanagement;

import hsl.devspace.app.admin.usermanagement.spring.controller.UserController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by hsenid on 9/21/16.
 */
//@ContextConfiguration(locations = {"file:/spring-usermanagement-config.xml"})
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class UserControllerTest extends AbstractTestNGSpringContextTests {

    private static final Logger LOG = LogManager.getLogger(UserControllerTest.class);


    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    public UserController userController;


    @Before
    public void setup(){

    }

    @Test
    public void someTest(){

       assertTrue(true);

    }



}
