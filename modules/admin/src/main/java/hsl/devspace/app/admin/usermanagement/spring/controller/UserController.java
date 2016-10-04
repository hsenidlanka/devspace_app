package hsl.devspace.app.admin.usermanagement.spring.controller;



import hsl.devspace.app.corelogic.domain.User;
import hsl.devspace.app.corelogic.repository.user.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

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
    private UserRepository staffRepository;


/*ApplicationContext context=new ClassPathXmlApplicationContext("admin-integration-context.xml");
    StaffRepositoryImpl staffRepository= (StaffRepositoryImpl) context.getBean("staffRepository");*/



    @RequestMapping(value="/list")
    public ModelAndView listContact(ModelAndView model)  {
//        User newContact = new User();;
//        validator.addObject("contact", newContact);
        model.setViewName("home");
        return model;
    }



//    customer user add is done in below methods
    @RequestMapping(value="/add",method=RequestMethod.GET)
    public ModelAndView showCustomer(){
        return new ModelAndView("userAdd", "command",new User());
    }

    //controller method to validate the uniquness of username
/*    @RequestMapping(value="/uniqueUsername",method=RequestMethod.POST)
    public @ResponseBody String checkUser(@RequestParam("username") String uname ){
        boolean usernameUnique ;
       String returnType;

        usernameUnique=staffRepository.checkUsernameUnique(uname);
        if(!usernameUnique){
                returnType= "Username available" + uname;
        }else
                returnType="Correct new username" + uname;

        return returnType;
    }*/


    @RequestMapping(value="/addCustomer",method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("newUser")  User newUser,
                               final RedirectAttributes redirectAttributes) throws SQLIntegrityConstraintViolationException {

        boolean usernameUnique=staffRepository.checkUsernameUnique(newUser);
        if(usernameUnique) {
            int i = staffRepository.add(newUser);
            if (i == 0)
                return "redirect:add";
            else {
                redirectAttributes.addFlashAttribute("newUser", newUser);
                redirectAttributes.addFlashAttribute("message", "Added Succcessfully");
                return "redirect:add";
            }
        }
        return "redirect:list";
    }

    @RequestMapping(value="/showUser", method=RequestMethod.GET)
    public String showCustomer(@ModelAttribute("newUser") User newUser) {
        System.out.println("cust:" + newUser.getFirstName());
        return "showUser";
    }


    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView showCustomerList(){
        return new ModelAndView("usersView", "command",new User());

    }

    //handler method to retrieve the details of a particular user
    @RequestMapping(value = "/view/customerTable", method = RequestMethod.GET)
    public @ResponseBody List<Map<String, Object>> viewUser(HttpServletRequest request){
        List<Map<String, Object>> staffList = staffRepository.view();

        return staffList;
    }







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
        ModelAndView validator=new ModelAndView("editUser");// jsp form to view user details and edit
        validator.addObject("user",user);
        return validator;
    }*/