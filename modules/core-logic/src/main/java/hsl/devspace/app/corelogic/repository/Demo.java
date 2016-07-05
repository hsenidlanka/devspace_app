package hsl.devspace.app.corelogic.repository;

import hsl.devspace.app.corelogic.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by hsenid on 7/5/16.
 */
public class Demo {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-user.xml");
/*
        UserRepository userdemo = (UserRepository) context.getBean("userRepository");
        userdemo.addUser(new User("aa","bb"));*/

        UserRepository userdemo = (UserRepository) context.getBean("userRepository");
        User user1 = new User("test3","test4");
        userdemo.addUser(user1);

    }
}
