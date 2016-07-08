package hsl.devspace.app.coreserver.common;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by hsenid on 7/7/16.
 */
public class DatabaseConnection {
    private static final Logger log = LogManager.getLogger(DatabaseConnection.class);
    private static ComboPooledDataSource comboPooledDataSource;

    public static void createComboPooledDataSource(){
        log.info("Creating a ComboPooledDataSource.");
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        comboPooledDataSource= (ComboPooledDataSource) context.getBean("datasource");
        log.info("Created a ComboPooledDataSource.");
    }

    public static ComboPooledDataSource getComboPooledDataSource(){
        return comboPooledDataSource;
    }

    public static void closeComboPooledDataSource(){
        log.info("Closing a ComboPooledDataSource.");
        if(getComboPooledDataSource()!=null){
            getComboPooledDataSource().close();
        }
        log.info("Closed a ComboPooledDataSource.");
    }
}
