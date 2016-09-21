package hsl.devspace.app.admin.usermanagement.spring.controller;

import hsl.devspace.app.admin.usermanagement.spring.model.User;
import hsl.devspace.app.corelogic.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * controller class containing handler methods which perform necessary handler methods
 */

@Controller
@RequestMapping(value="/users")
public class UserController {

    private static final Logger LOG = LogManager.getLogger(UserController.class);

    /*  this annotation allows Spring inject an instance of UserRepositoryImpl into this controller automatically.
       Each handler method uses this UserRepository object to perform necessary CRUD operation*/
    @Autowired
    private UserRepository user1;

/*    ApplicationContext context =
            new ClassPathXmlApplicationContext("admin-servlet.xml");

    UserRepository user1= (UserRepository)context.getBean("userRepo");*/
//    UserRepository userRepository = (UserRepository) context.getBean("userRepository");


 /*   ApplicationContext context= new ClassPathXmlApplicationContext("admin-servlet.xml");
    UserRepository userRepository= (UserRepository) context.getBean("userRepository")*/;




    @RequestMapping(value="/list")
    public ModelAndView listContact(ModelAndView model)  {
        User newContact = new User();;
        model.addObject("contact", newContact);
        model.setViewName("home");
        return model;
    }

    //handler method to list all the users
 /*  @RequestMapping(value="/list")
    public ModelAndView listUsers(ModelAndView model) throws IOException {
        List<User> listUsers= userRepository.list();
        model.addObject("listUsers",listUsers);
        model.setViewName("users");// view the user list page
        return model;
    }*/

    @RequestMapping(value="/add")
    public ModelAndView saveOrUpdate(@ModelAttribute("userForm")hsl.devspace.app.corelogic.domain.User newUser) throws SQLIntegrityConstraintViolationException {
        ModelAndView model = new ModelAndView();
//
       int i= user1.add(newUser);
        if(i ==1)
            model.setViewName("userAdd");
        else
         System.out.print("Error in add user");
        return model;

    }

    //handler method to retrieve the details of a particular user
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView viewUser(HttpServletRequest request){
        String uname= request.getParameter("username");
//        User user=  userRepository.get(uname);
        ModelAndView model=new ModelAndView("usersView");// jsp form to view user details and edit
//        model.addObject("user",user);
        return model;
    }
    //handler method for inserting or updating a user record
 /*   @RequestMapping(value = "/userAdd")
    public ModelAndView saveUser(@ModelAttribute User user){
        ModelAndView model = new ModelAndView();
//        userRepository.saveOrUpdate(user);
//        return new ModelAndView("userManagement/userAdd");
        model.setViewName("userAdd");
        return model;*/


}

//handler method to delete a user record
   /* @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteUser(HttpServletRequest request){
        String uname= request.getParameter("username");
        userRepository.delete(uname);
        return new ModelAndView("redirect:/");
    }

    //handler method to retrieve the details of a particular user
    @RequestMapping(value = "/userDetail", method = RequestMethod.GET)
    public ModelAndView viewUser(HttpServletRequest request){
        String uname= request.getParameter("username");
        User user=  userRepository.get(uname);
        ModelAndView model=new ModelAndView("editUser");// jsp form to view user details and edit
        model.addObject("user",user);
        return model;
    }*/



