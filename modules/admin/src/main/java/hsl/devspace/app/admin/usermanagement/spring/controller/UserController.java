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

import javax.swing.*;
import java.sql.SQLException;
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
    private UserRepository staffRepository, customerRepository;


    @RequestMapping(value="/list")
    public ModelAndView listContact(ModelAndView model)  {
        model.setViewName("home");
        return model;
    }



//    customer user add is done in below methods
    @RequestMapping(value="/add",method=RequestMethod.GET)
    public ModelAndView showCustomer(){
        return new ModelAndView("user_management/userAdd", "command",new User());
    }



    @RequestMapping(value="/addCustomer",method = RequestMethod.POST)
    public ModelAndView saveOrUpdate(@ModelAttribute("newUser")  User newUser,
                                     @RequestParam("radioName") String userType,BindingResult validationResult) throws SQLIntegrityConstraintViolationException {

        if (validationResult.hasErrors()) {
            new ModelAndView("user_management/userAdd", "command", newUser);
        }

        if (userType.equals("staff")) {
            boolean usernameUnique = staffRepository.checkUsernameUnique(newUser);
            if (usernameUnique) {
                int i = staffRepository.addStaffMember(newUser);
                if (i == 0)
                    JOptionPane.showMessageDialog(null, "Server side error...Could not insert");// put separate error pages
                else {
//                    return new ModelAndView(new RedirectView("successPage"));
                    JOptionPane.showMessageDialog(null,"You have successfully added the user..!!");
                }
            } else {
                JOptionPane.showMessageDialog(null,"Error.. Username Exists already");
                return new ModelAndView("user_management/userAdd", "command", newUser);
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
                return new ModelAndView("user_management/userAdd", "command", newUser);
            }
        }
        return new ModelAndView(new RedirectView("add"));
    }


    @RequestMapping(value="/showUser", method=RequestMethod.GET)
    public String showCustomer(@ModelAttribute("newUser") User newUser) {
        System.out.println("cust:" + newUser.getFirstName());
        return "user_management/showUser";
    }

    /** to handle the active users
     * includes active staff and customer users**/

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView showCustomerList(){
        return new ModelAndView("user_management/usersView", "command",new User());

    }

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
    public @ResponseBody List<Map<String, Object>> viewCustomer(@ModelAttribute("newUser")  User customerUser){
        List<Map<String, Object>> outc = new ArrayList<Map<String, Object>>();
        List<User> customerList= customerRepository.selectActiveUsers();

        for (int i=0;i<customerList.size();i++){
            customerUser=customerList.get(i);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", customerUser.getId());
            map.put("username", customerUser.getUsername());
            map.put("first_name",customerUser.getFirstName());
            map.put("last_name", customerUser.getLastName());
            map.put("mobile", customerUser.getMobile());
            map.put("email", customerUser.getEmail());
            map.put("address_line3", customerUser.getAddressL3());
            map.put("registered_date", customerUser.getRegDate());

            LOG.info("newUser {}", customerUser);
            outc.add(map);
            LOG.info("out {}",outc);
        }
        return outc;
    }


    /** to handle the blocked users
     * includes blocked customers and blocked staff members**/
     @RequestMapping(value = "/viewBlocked", method = RequestMethod.GET)
    public ModelAndView showBlockedList(){
        return new ModelAndView("user_management/usersBanned", "command",new User());

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
    public @ResponseBody List<Map<String, Object>>  viewBannedCustomer(@ModelAttribute("newUser")  User customerUserb){
        List<Map<String, Object>> outcb = new ArrayList<Map<String, Object>>();
        List<User> bannedcustomerList = customerRepository.selectBlockedUsers();

        for (int i=0;i<bannedcustomerList.size();i++){
            customerUserb=bannedcustomerList.get(i);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", customerUserb.getId());
            map.put("username", customerUserb.getUsername());
            map.put("first_name",customerUserb.getFirstName());
            map.put("last_name", customerUserb.getLastName());
            map.put("mobile", customerUserb.getMobile());
            map.put("email", customerUserb.getEmail());
            map.put("address_line3", customerUserb.getAddressL3());
            map.put("registered_date", customerUserb.getRegDate());

            LOG.info("newUser {}", customerUserb);
            outcb.add(map);
            LOG.info("out {}",outcb);
        }

        return outcb;
    }
    //handler method to block a user customer record
    @RequestMapping(value = "/block/customer", method = RequestMethod.GET)
    public @ResponseBody int blockCustomer(@RequestParam("uname") String uname){

        int i=customerRepository.block(uname);
        return i;
    }

    //handler method to block a staff record
    @RequestMapping(value = "/block/staff", method = RequestMethod.GET)
    public @ResponseBody int blockStaff(@RequestParam("uname") String uname){

        int i=staffRepository.block(uname);
        return i;
    }

    //handler method to unblock a staff record
    @RequestMapping(value = "/unblock/staff", method = RequestMethod.GET)
    public @ResponseBody int unblockStaff(@RequestParam("uname") String uname){

        int i=staffRepository.unblock(uname);
        return i;
    }

    //handler method to unblock a customer record
    @RequestMapping(value = "/unblock/customer", method = RequestMethod.GET)
    public @ResponseBody int unblockCustomer(@RequestParam("uname") String uname){

        int i=customerRepository.unblock(uname);
        return i;
    }


    //    customer user edit form is displayed in below handler method
    @RequestMapping(value="customer/edit",method=RequestMethod.GET)
    public @ResponseBody User showEditCustomer(@RequestParam("uname") String uname){

        LOG.error("uname is {}",uname);

        User customer=customerRepository.retrieveSelectedUserDetails(uname);
        LOG.error("customer object {}",customer);

        return customer ;
    }


    //handler method for sending customer edit form data to database
    @RequestMapping(value="customer/editCustomer",method=RequestMethod.POST)
    public ModelAndView editCustomer(@ModelAttribute("customer") User customer) throws SQLException {

        boolean usernameUnique = customerRepository.checkUsernameUnique(customer);
        if (usernameUnique) {
            int i=customerRepository.update(customer);
            if (i == 1)
                JOptionPane.showMessageDialog(null,"You have successfully updated the user Customer..!!");

        }else {
            JOptionPane.showMessageDialog(null,"Error.. Username Exists already");

        }

        return new ModelAndView(new RedirectView("customer/edit"));
    }

    //   staff user edit form is displayed in below handler method
    @RequestMapping(value="staff/edit",method=RequestMethod.GET)
    public @ResponseBody User showEditStaff(@RequestParam("staff_uname") String staff_uname){

        LOG.error("uname is {}",staff_uname);

        User staff=staffRepository.retrieveSelectedUserDetails(staff_uname);
        LOG.error("staff object {}",staff);

        return staff ;
    }

    //   Banned staff user detail form is displayed here
    @RequestMapping(value="bannedStaff/detail",method=RequestMethod.GET)
    public @ResponseBody User showBannedStaff(@RequestParam("bstaff_uname") String bstaff_uname){

        LOG.error("uname is {}",bstaff_uname);

        User staff=staffRepository.retrieveSelectedUserDetails(bstaff_uname);
        LOG.error("staff object {}",staff);

        return staff ;
    }

    //   Banned customer user detail form is displayed here
    @RequestMapping(value="bannedCustomer/detail",method=RequestMethod.GET)
    public @ResponseBody User showBannedCustomer(@RequestParam("bcustomer_uname") String bcustomer_uname){

        LOG.error("uname is {}",bcustomer_uname);

        User customer=customerRepository.retrieveSelectedUserDetails(bcustomer_uname);
        LOG.error("staff object {}",customer);

        return customer ;
    }


}




    //handler method to retrieve the details of a particular user
 /*   @RequestMapping(value = "/userDetail", method = RequestMethod.GET)
    public ModelAndView viewUser(HttpServletRequest request){
        String uname= request.getParameter("username");
        User user=  userRepository.get(uname);
        ModelAndView validator=new ModelAndView("editUser");// jsp form to view user details and edit
        validator.addObject("user",user);
        return validator;
    }*/