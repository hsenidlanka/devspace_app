package hsl.devspace.app.corelogic.repository.user;

import hsl.devspace.app.corelogic.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

/**
 * Created by hsenid on 9/19/16.
 */
public class GuestApp {
    public static void main(String[] args) throws SQLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-user.xml");
        StaffRepositoryImpl userdemo = (StaffRepositoryImpl) context.getBean("staffRepository");
        User user=new User("ms","sepali","sss","1jh2ih","1w2rg","1se2rtg","1fg2","21er","1et2h","e12r","admin","h21h","f12h");
       userdemo.checkUsernameUnique("Sysadmin");
    }
}
