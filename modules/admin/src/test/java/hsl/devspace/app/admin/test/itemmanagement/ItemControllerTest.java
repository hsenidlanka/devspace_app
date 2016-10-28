package hsl.devspace.app.admin.test.itemmanagement;

import hsl.devspace.app.admin.test.itemmanagement.spring.controller.ItemController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

@Test
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class ItemControllerTest extends AbstractTransactionalTestNGSpringContextTests{

    @Autowired
    ItemController itemController;

    @Test
    void testAddItem(){
        
    }

}
