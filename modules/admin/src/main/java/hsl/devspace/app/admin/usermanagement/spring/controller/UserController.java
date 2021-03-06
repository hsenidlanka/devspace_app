package hsl.devspace.app.admin.usermanagement.spring.controller;


import hsl.devspace.app.corelogic.domain.User;
import hsl.devspace.app.corelogic.repository.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import javax.validation.Valid;
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

    /** reading the ValidationMessages property file using annotations**/
    @Value("${insert.server.error}")
    private String insertError;

    @Value("${insert.staff.success}")
    private String insertSuccessS;

    @Value("${insert.user}")
    private String insertUnsuccess;

    @Value("${insert.customer.success}")
    private String insertSuccessC;

    @Value("${update.customer.success}")
    private String updateSuccess;

    @Value("${update.customer}")
    private String updateFail;

    @Value("${update.staff.success}")
    private String updateSuccessStaff;


    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    /*  this annotation allows Spring inject an instance of UserRepositoryImpl into this controller automatically.
       Each handler method uses this UserRepository object to perform necessary CRUD operation*/

    @Autowired
    private UserRepository staffRepository, customerRepository;


/////////////////////////////////////////////////////  USER ADD HANDLER METHODS //////////////////////////////////////////////////////

//    customer user add is done in below methods
    @RequestMapping(value="/add",method=RequestMethod.GET)
    public ModelAndView showCustomer(){
        return new ModelAndView("user_management/userAdd", "newUser",new User());
    }

    @RequestMapping(value="/addCustomer",method = RequestMethod.POST)
    public ModelAndView saveOrUpdate(@ModelAttribute("newUser") @Valid User newUser,BindingResult validationResult,
                                     @RequestParam("radioName") String userType,
                                     HttpServletRequest request, HttpServletResponse response) throws SQLIntegrityConstraintViolationException {

        if (validationResult.hasErrors()) {
            return new ModelAndView("user_management/userAdd");
        }

        if (userType.equals("staff")) {
            boolean usernameUnique = staffRepository.checkUsernameUnique(newUser);
            if (usernameUnique) {
                int i = staffRepository.addStaffMember(newUser);
                if (i == 0)
                    JOptionPane.showMessageDialog(null,insertError);// put separate error pages
                else {
//                    return new ModelAndView(new RedirectView("successPage"));
                    JOptionPane.showMessageDialog(null,insertSuccessS);
                }
            } else {
                JOptionPane.showMessageDialog(null, insertUnsuccess);
                return new ModelAndView("user_management/userAdd", "newUser", newUser);
            }
        }
        if(userType.equals("customer")){
            boolean usernameUnique = customerRepository.checkUsernameUnique(newUser);
            if (usernameUnique) {
                int i = customerRepository.addCustomer(newUser);
                if (i == 1)
                    JOptionPane.showMessageDialog(null,insertSuccessC);
            }else {
//                validationResult.rejectValue("username", "error.username.exists", "The username is already in use.");
                JOptionPane.showMessageDialog(null, insertUnsuccess);
                return new ModelAndView("user_management/userAdd", "newUser", newUser);
            }
        }
        return new ModelAndView(new RedirectView("add"));
    }


///////////////////////////////////////////////////// ACTIVE USERS VIEW HANDLER METHODS WITH PAGINATION  ///////////////////////////////////////

    /** to handle the active users
     * includes active staff and customer users**/

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView showCustomerList(){
        return new ModelAndView("user_management/usersView", "command",new User());

    }

    //handler method to retrieve the details of a particular staff user
    @RequestMapping(value = "/view/staffTable", method = RequestMethod.GET)
    public @ResponseBody  List<Map<String, Object>> viewStaff(@ModelAttribute("newUser")  User staffUser,
                                                              @RequestParam("pageLimit") String pageLimit,
                                                              @RequestParam("initPage") String initPage){

        int initPg = Integer.parseInt(initPage);
        int limitPg = Integer.parseInt(pageLimit);

        List<Map<String, Object>> out = new ArrayList<Map<String, Object>>();
        List<User> staffList = staffRepository.selectActiveUsers(limitPg,initPg);

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

    /*
*getting record count for loading Staff table with pagination ACTIVE
**/
    @RequestMapping(value = "/StaffPaginationTable", method = RequestMethod.GET)
    public @ResponseBody int loadPagination1(){
        LOG.info("Category Count is {}",staffRepository.countUsers("active"));
        return staffRepository.countUsers("active");
    }

    //handler method to retrieve the details of a particular customer user
    @RequestMapping(value = "/view/customerTable", method = RequestMethod.GET)
    public @ResponseBody List<Map<String, Object>> viewCustomer(@ModelAttribute("newUser")  User customerUser,
                                                                @RequestParam("pageLimit") String pageLimit,
                                                                @RequestParam("initPage") String initPage){

        int initPg = Integer.parseInt(initPage);
        int limitPg = Integer.parseInt(pageLimit);

        List<Map<String, Object>> outc = new ArrayList<Map<String, Object>>();
        List<User> customerList= customerRepository.selectActiveUsers(limitPg,initPg);

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

    /*
*getting record count for loading Customer table with pagination (ACTIVE)
**/
    @RequestMapping(value = "/CustomerPaginationTable", method = RequestMethod.GET)
    public @ResponseBody int loadPagination2(){
        LOG.info("Category Count is {}",customerRepository.countUsers("active"));
        return customerRepository.countUsers("active");
    }


//////////////////////////////////////  BLOCKED USERS VIEW IN A TABLE HANDLER METHODS  ///////////////////////////////////////////////////

    /** to handle the blocked users
     * includes blocked customers and blocked staff members**/
     @RequestMapping(value = "/viewBlocked", method = RequestMethod.GET)
    public ModelAndView showBlockedList(){
        return new ModelAndView("user_management/usersBanned", "command",new User());

    }

    //handler method to retrieve the details of a particular banned staff user
    @RequestMapping(value = "/view/bannedstaffTable", method = RequestMethod.GET)
    public @ResponseBody  List<Map<String, Object>> viewBannedStaff(@ModelAttribute("newUser")  User staffUserb,
                                                                    @RequestParam("pageLimit") String pageLimit,
                                                                    @RequestParam("initPage") String initPage){

        int initPg = Integer.parseInt(initPage);
        int limitPg = Integer.parseInt(pageLimit);

        List<Map<String, Object>> outb = new ArrayList<Map<String, Object>>();
        List<User> bannedstaffList = staffRepository.selectBlockedUsers(limitPg,initPg);

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
    /*
    *getting record count for loading Staff table with pagination ACTIVE
    **/
    @RequestMapping(value = "/BannedStaffPaginationTable", method = RequestMethod.GET)
    public @ResponseBody int loadPaginationBanned1(){
        LOG.info("Category Count is {}",staffRepository.countUsers("inactive"));
        return staffRepository.countUsers("inactive");
    }

    //handler method to retrieve the details of a particular banned customer user
    @RequestMapping(value = "/view/bannedcustomerTable", method = RequestMethod.GET)
    public @ResponseBody List<Map<String, Object>>  viewBannedCustomer(@ModelAttribute("newUser")  User customerUserb,
                                                                       @RequestParam("pageLimit") String pageLimit,
                                                                       @RequestParam("initPage") String initPage){
        int initPg = Integer.parseInt(initPage);
        int limitPg = Integer.parseInt(pageLimit);

        List<Map<String, Object>> outcb = new ArrayList<Map<String, Object>>();
        List<User> bannedcustomerList = customerRepository.selectBlockedUsers(limitPg,initPg);

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

    /*
*getting record count for loading Customer table with pagination (ACTIVE)
**/
    @RequestMapping(value = "/BannedCustomerPaginationTable", method = RequestMethod.GET)
    public @ResponseBody int loadPaginationBanned2(){
        LOG.info("Category Count is {}",customerRepository.countUsers("inactive"));
        return customerRepository.countUsers("inactive");
    }
///////////////////////////////////////////////  USER BLOCK AND UNBLOCK HANDLER METHODS  ///////////////////////////////////////////

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

/////////////////////////////////////////  USER UPDATE HANDLER METHODS   ///////////////////////////////////////////////////////

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
    public ModelAndView editCustomer(@ModelAttribute("customer")  User customer,BindingResult validationResult)
                                     throws SQLException {

        String n1=customer.getFirstName();
        String n2=customer.getLastName();
        String n3=customer.getAddressL3();
        String n4=customer.getUsername();
        String n5=customer.getPassword();

        LOG.info("EDIT CUSTOMER fname {}",n1);
        LOG.info("EDIT CUSTOMER lname {}",n2);
        LOG.info("EDIT CUSTOMER city {}",n3);
        LOG.info("EDIT CUSTOMER uname {}",n4);
        LOG.info("EDIT CUSTOMER password{}",n5);

            customer.setUsername(n4);
            int i=customerRepository.update(customer);
            LOG.error("EDIT CUSTOMER i {}",i);

            if (i == 1)
                JOptionPane.showMessageDialog(null, updateSuccess);
            else
                JOptionPane.showMessageDialog(null,updateFail);

        return new ModelAndView(new RedirectView("/admin/users/view"));
    }

    //   staff user edit form is displayed in below handler method
    @RequestMapping(value="staff/edit",method=RequestMethod.GET)
    public @ResponseBody User showEditStaff(@RequestParam("staff_uname") String staff_uname){

        LOG.info("uname is {}",staff_uname);

        User staff=staffRepository.retrieveSelectedUserDetails(staff_uname);
        LOG.info("staff object {}",staff);

        return staff ;
    }


    //handler method for sending customer edit form data to database
    @RequestMapping(value="staff/editStaff",method=RequestMethod.POST)
    public ModelAndView editStaff(@ModelAttribute("staff") User staff) throws SQLException {

        String n1=staff.getFirstName();
        String n2=staff.getLastName();
        String n3=staff.getAddressL3();
        String n4=staff.getUsername();
        String n5=staff.getPassword();

        LOG.info("EDIT STAFF fname {}",n1);
        LOG.info("EDIT STAFF lname {}",n2);
        LOG.info("EDIT STAFF city {}",n3);
        LOG.info("EDIT STAFF uname {}",n4);
        LOG.info("EDIT STAFF password{}",n5);

        staff.setUsername(n4);
        int i=staffRepository.updateStaffMember(staff);
        LOG.error("EDIT CUSTOMER i {}",i);

        if (i == 1)
            JOptionPane.showMessageDialog(null, updateSuccessStaff);
        else
            JOptionPane.showMessageDialog(null,updateFail);

        return new ModelAndView(new RedirectView("/admin/users/view"));
    }


////////////////////////////////////////////////  BLOCKED USER DETAIL VIEW HANDLER METHODS   ///////////////////////////////////////////////////

    //   Banned staff user detail form is displayed here
    @RequestMapping(value="bannedStaff/detail",method=RequestMethod.GET)
    public @ResponseBody User showBannedStaff(@RequestParam("bstaff_uname") String bstaff_uname){

        LOG.info("uname is {}",bstaff_uname);

        User staff=staffRepository.retrieveSelectedUserDetails(bstaff_uname);
        LOG.info("staff object {}",staff);

        return staff ;
    }

    //   Banned customer user detail form is displayed here
    @RequestMapping(value="bannedCustomer/detail",method=RequestMethod.GET)
    public @ResponseBody User showBannedCustomer(@RequestParam("bcustomer_uname") String bcustomer_uname){

        LOG.info("uname is {}",bcustomer_uname);

        User customer=customerRepository.retrieveSelectedUserDetails(bcustomer_uname);
        LOG.info("staff object {}",customer);

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