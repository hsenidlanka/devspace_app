package hsl.devspace.app.corelogic.repository.user;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.Random;

/**
 * Created by hsenid on 7/5/16.
 */
public class UserRepositoryApp {
    public static void main(String[] args) throws SQLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-user.xml");
        UserRepository userdemo = (UserRepository) context.getBean("userRepository");
      //userdemo.add(new User("didi", "aad1","134rr","nipuni","y3hg1y","yhf13g","u13g","g1v3","t31","1h3","fg","hh","hh"));
       /* User user=new User("fhjf","jhrujhg");*/
        userdemo.loginAuthenticate("ad1","134rr");
        //User user1 = new User("d111","dindd","26576","hgdf","1378","uei","djki");
       // userdemo.addUser(user1);
        /*String arr[]={"d400","d4001","d4003","d4005","d40045","rhj"};
        for(int i=0;i<arr.length;i++) {
            userdemo.delete(arr[i]);
        }*/
      // userdemo.retrieveMultipleRowsColumns("gh3g1");
       // userdemo.add(user1);
       // userdemo.changePassword("d111","sw","dineesha");


       /* userdemo.retrieveByDateRange(Date.valueOf("2000-12-12"), Date.valueOf("2001-11-11"));
userdemo.countUsers();*/
        String[] array={"s2","o"} ;
            int rnd = new Random().nextInt(array.length);
        System.out.println(array[rnd]);



    }
}
