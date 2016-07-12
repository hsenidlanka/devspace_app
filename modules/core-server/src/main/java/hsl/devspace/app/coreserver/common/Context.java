package hsl.devspace.app.coreserver.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by hsenid on 7/12/16.
 */
public class Context {
    public static ApplicationContext appContext;

    static {
        appContext=new ClassPathXmlApplicationContext("spring-context.xml");
    }
}
