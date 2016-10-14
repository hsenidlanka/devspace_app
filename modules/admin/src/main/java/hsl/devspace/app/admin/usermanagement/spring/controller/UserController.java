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
import java.util.ArrayList;
import java.util.HashMap;
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
    private UserRepository customerRepository;

    @Autowired
    private UserRepository staffRepository;


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
                    //return the user add success message
                    JOptionPane.showMessageDialog(null,"You have successfully added the user..!!");
                }
            } else {
                //return the unique username existance message
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


    //controller method to show a perticular user detail
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

/*    @RequestMapping(value = "/view/staffTable", method = RequestMethod.GET)
    public @ResponseBody  List<User>  viewStaff(HttpServletRequest request){
        List<User> staffList = staffRepository.selectActiveUsers();

        return staffList;
    }*/
    //handler method to retrieve the details of a particular staff user
    @RequestMapping(value = "/view/staffTable", method = RequestMethod.GET)
    public @ResponseBody  List<Map<String, Object>> viewStaff(@ModelAttribute("newUser")  User staffUser){
        List<Map<String, Object>> out = new ArrayList<Map<String, Object>>();
        List<User> staffList = staffRepository.selectActiveUsers();

        for (int i=0;i<staffList.size();i++){
            staffUser=staffList.get(i);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", staffUser.getId());
            map.put("username", staffUser.getUsername());
            map.put("first_name", staffUser.getFirstName());
            map.put("last_name", staffUser.getLastName());
            map.put("mobile", staffUser.getMobile());
            map.put("designation", staffUser.getDesignation());
            map.put("department", staffUser.getDepartment());
            map.put("branch", staffUser.getBranch());

            LOG.info("newUser {}", staffUser);

            out.add(map);
            LOG.info("out {}",out);

        }

        return out ;
    }

    //handler method to retrieve the details of a particular customer user
    @RequestMapping(value = "/view/customerTable", method = RequestMethod.GET)
    public @ResponseBody List<User> viewCustomer(){
        List<User> customerList= customerRepository.selectActiveUsers();
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
    public @ResponseBody  List<Map<String, Object>> viewBannedStaff(@ModelAttribute("newUser")  User staffUserb){
        List<Map<String, Object>> outb = new ArrayList<Map<String, Object>>();
        List<User> bannedstaffList = staffRepository.selectBlockedUsers();

        for (int i=0;i<bannedstaffList.size();i++){
            staffUserb=bannedstaffList.get(i);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", staffUserb.getId());
            map.put("username", staffUserb.getUsername());
            map.put("first_name", staffUserb.getFirstName());
            map.put("last_name", staffUserb.getLastName());
            map.put("mobile", staffUserb.getMobile());
            map.put("designation", staffUserb.getDesignation());
            map.put("department", staffUserb.getDepartment());
            map.put("branch", staffUserb.getBranch());

            LOG.info("newUser {}", staffUserb);

            outb.add(map);
            LOG.info("out {}",outb);

        }


        return outb;
    }

    //handler method to retrieve the details of a particular banned customer user
    @RequestMapping(value = "/view/bannedcustomerTable", method = RequestMethod.GET)
    public @ResponseBody List<User> viewBannedCustomer(HttpServletRequest request){
        List<User> bannedcustomerList = customerRepository.selectBlockedUsers();

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