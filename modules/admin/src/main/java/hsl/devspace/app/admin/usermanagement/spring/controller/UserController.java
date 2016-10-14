package hsl.devspace.app.admin.usermanagement.spring.controller;


import hsl.devspace.app.corelogic.domain.User;
import hsl.devspace.app.corelogic.repository.user.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
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
    private UserRepository staffRepository, customerRepository;


    @RequestMapping(value="/list")
    public ModelAndView listContact(ModelAndView model)  {
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

/*
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
    }*/


    @RequestMapping(value="/addCustomer",method = RequestMethod.POST)
    public ModelAndView saveOrUpdate(@ModelAttribute("newUser")  User newUser,
                                     @RequestParam("radioName") String userType,BindingResult validationResult) throws SQLIntegrityConstraintViolationException {

        if (validationResult.hasErrors()) {
            new ModelAndView("userAdd", "command", newUser);
        }

        if (userType.equals("staff")) {
            boolean usernameUnique = staffRepository.checkUsernameUnique(newUser);
            if (usernameUnique) {
                int i = staffRepository.add(newUser);
                if (i == 0)
                    JOptionPane.showMessageDialog(null, "Server side error...Could not insert");// put separate error pages
                else {
//                    return new ModelAndView(new RedirectView("successPage"));
                    JOptionPane.showMessageDialog(null,"You have successfully added the user..!!");
                }
            } else {
                JOptionPane.showMessageDialog(null,"Error.. Username Exists already");
                return new ModelAndView("userAdd", "command", newUser);
            }
        }
        if(userType.equals("customer")){
            boolean usernameUnique = customerRepository.checkUsernameUnique(newUser);
            if (usernameUnique) {
                int i = customerRepository.add(newUser);
                if (i == 1)
                    JOptionPane.showMessageDialog(null,"You have successfully added the user Customer..!!");
            }else {
//                validationResult.rejectValue("username", "error.username.exists", "The username is already in use.");
                JOptionPane.showMessageDialog(null,"Error.. Username Exists already");
                return new ModelAndView("userAdd", "command", newUser);
            }
        }
        return new ModelAndView(new RedirectView("add"));
    }


    @RequestMapping(value="/showUser", method=RequestMethod.GET)
    public String showCustomer(@ModelAttribute("newUser") User newUser) {
        System.out.println("cust:" + newUser.getFirstName());
        return "showUser";
    }

    /** to handle the active users
     * includes active staff and customer users**/

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView showCustomerList(){
        return new ModelAndView("usersView", "command",new User());

    }

    //handler method to retrieve the details of a particular staff user
    @RequestMapping(value = "/view/staffTable", method = RequestMethod.GET)
    public @ResponseBody List<Map<String, Object>> viewStaff(HttpServletRequest request){
        List<Map<String, Object>> staffList = staffRepository.selectActiveUsers();

        return staffList;
    }

    //handler method to retrieve the details of a particular customer user
    @RequestMapping(value = "/view/customerTable", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, Object>> viewCustomer(HttpServletRequest request){
        List<Map<String, Object>> customerList = customerRepository.selectActiveUsers();

        return customerList;
    }

    /** to handle the blocked users
     * includes blocked customers and blocked staff members**/

     @RequestMapping(value = "/viewBlocked", method = RequestMethod.GET)
    public ModelAndView showBlockedList(){
        return new ModelAndView("usersBanned", "command",new User());

    }

    //handler method to retrieve the details of a particular banned staff user
    @RequestMapping(value = "/view/bannedstaffTable", method = RequestMethod.GET)
    public @ResponseBody List<Map<String, Object>> viewBannedStaff(HttpServletRequest request){
        List<Map<String, Object>> bannedstaffList = staffRepository.selectBlockedUsers();

        return bannedstaffList;
    }

    //handler method to retrieve the details of a particular banned customer user
    @RequestMapping(value = "/view/bannedcustomerTable", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, Object>> viewBannedCustomer(HttpServletRequest request){
        List<Map<String, Object>> bannedcustomerList = customerRepository.selectBlockedUsers();

        return bannedcustomerList;
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