package hsl.devspace.app.admin.usermanagement.spring.controller;

import hsl.devspace.app.corelogic.domain.User;
import hsl.devspace.app.corelogic.repository.user.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This controller class will handle all the ajax requests
 * for filtering the searching users
 */
@Controller
@RequestMapping(value = "/userFilters")
public class FilterController {

    private static final Logger LOG = LogManager.getLogger(FilterController.class);

    @Autowired
    private UserRepository staffRepository, customerRepository;


////////////////////////////////////////////////  ACTIVE USER Filter options handle //////////////////////////////////////////

    /** handler  method to filter
     * the search data on the registered date**/
    @RequestMapping(value = "/customerTable/date", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, Object>> dateFilteredCustomer(@RequestParam("from") String from, @RequestParam("to") String to,
                                                @RequestParam("name") String name)
                                                throws ParseException {

        //convert java.util time to sql time
        SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy");
        Date fromDate= sdf.parse(from);
        Date toDate= sdf.parse(to);
        java.sql.Date sqlfromDate= new java.sql.Date(fromDate.getTime());
        java.sql.Date sqltoDate= new java.sql.Date(toDate.getTime());

        LOG.error("fromSql,{}",sqlfromDate);
        LOG.error("toSql {}",sqltoDate);
        LOG.error("name {}",name);


        List<Map<String, Object>> outDate = new ArrayList<Map<String, Object>>();

        if((!from.equals("")) && (!to.equals(""))){
            List<User> customerList1= customerRepository.retrieveByDateRange(sqlfromDate,sqltoDate);

            for (int i=0;i<customerList1.size();i++){
                User customerUser=customerList1.get(i);
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
                outDate.add(map);
                LOG.info("out {}",outDate);
            }
        }
        return outDate ;
    }


    /** handler method  to filter
     * the search data on the registered date**/
    @RequestMapping(value = "/customerTable/city", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, Object>> cityFilteredCustomer( @RequestParam("city") String city) {

             LOG.error("city {}", city);
             List<Map<String, Object>> outCity = new ArrayList<Map<String, Object>>();
             List<User> customerList2=customerRepository.filterByCity(city);

            for (int i=0;i<customerList2.size();i++){
                User customerUser=customerList2.get(i);
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
                outCity.add(map);
                LOG.info("out {}",outCity);
            }
        return outCity;
         }

    /** handler method to filter
     * the search data on the registered date and the city**/
    @RequestMapping(value = "/customerTable/date/city", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, Object>> datecityFilteredCustomer(@RequestParam("from") String from, @RequestParam("to") String to,
                                                @RequestParam("city") String city) throws ParseException {


              //convert java.util time to sql time
              SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy");
              Date fromDate= sdf.parse(from);
              Date toDate= sdf.parse(to);
              java.sql.Date sqlfromDate= new java.sql.Date(fromDate.getTime());
              java.sql.Date sqltoDate= new java.sql.Date(toDate.getTime());


              ArrayList<Map<String, Object>> outCityDate1 = new ArrayList<Map<String, Object>>();
              ArrayList<Map<String, Object>> outCityDate2 = new ArrayList<Map<String, Object>>();

        //two lists of User objects obtained filtered by date and city respectively
              List<User> customerList3=customerRepository.retrieveByDateRange(sqlfromDate,sqltoDate);
              List<User> customerList4=customerRepository.filterByCity(city);

        //first filter by date range
        for (int i=0;i<customerList3.size();i++){
            User customerUser=customerList3.get(i);
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
            outCityDate1.add(map);
            LOG.error("out {}", outCityDate1);
        }

        //secondly filter by city
        for (int i=0;i<customerList4.size();i++){
            User customerUser=customerList4.get(i);
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
            outCityDate2.add(map);
            LOG.error("out {}",outCityDate2);
        }

        //to get the intersection of the two lists outCityDate1 and outCityDate2
        ArrayList<Map<String, Object>> common= new ArrayList<Map<String, Object>>(outCityDate1);
        common.retainAll(outCityDate2);
        LOG.error("The intersected list of the date and city filtered lists {}",common);

        return common;
    }


    /**Below handler methods are for the filter options of the Staff Users(Active) **/
    @RequestMapping(value = "/staffTable", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, Object>> FilteredStaff(@RequestParam("designation") String designation, @RequestParam("department")
                                            String department, @RequestParam("branch") String branch,
                                            @RequestParam("name") String name){


       List<Map<String, Object>> outStaff = new ArrayList<Map<String, Object>>();

        //query for designation select filtering
        if(!(designation.equals("--Select--"))) {
            List<Map<String, Object>> outStaff1 = new ArrayList<Map<String, Object>>();
            List<User> staffList1 = staffRepository.filterByDesignation(designation,"active");

            for (User staffUser : staffList1) {
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
                outStaff1.add(map);
                LOG.info("out {}", outStaff1);
            }
            LOG.error("Filter by designation {}", designation);
            return outStaff1;
        }
        //query for department select filtering
        if(!(department.equals("--Select--"))) {
            List<Map<String, Object>> outStaff2 = new ArrayList<Map<String, Object>>();
            List<User> staffList2 = staffRepository.filterByDepartment(department,"active");

            for (User staffUser : staffList2) {
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
                outStaff2.add(map);
                LOG.info("out {}", outStaff2);
            }
            LOG.error("Filter by department {}", department);
            return outStaff2;
        }

        //query for branch select filtering
        if(!(branch.equals("--Select--"))) {
            List<Map<String, Object>> outStaff3 = new ArrayList<Map<String, Object>>();
            List<User> staffList3 = staffRepository.filterByBranch(branch,"active");

            for (User staffUser : staffList3) {
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
                outStaff3.add(map);
                LOG.info("out {}", outStaff3);
            }
            LOG.error("Filter by branch {}", branch);
            return outStaff3;
        }
        return outStaff;
    }

//////////////////////////////////////////////////////  INACTIVE USER Filter options handling ////////////////////////////

    /**Below handler methods are for the filter options of
     ** the Customer Users(InActive) **/


    /** handler method  to filter
     * the search data on the registered date**/
    @RequestMapping(value = "/bannedcustomerTable/city", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, Object>> cityFilteredCustomerBlocked( @RequestParam("city") String city) {

        LOG.error("city {}", city);
        List<Map<String, Object>> outCity = new ArrayList<Map<String, Object>>();
        List<User> customerList2=customerRepository.filterByCity(city);

        for (int i=0;i<customerList2.size();i++){
            User customerUser=customerList2.get(i);
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
            outCity.add(map);
            LOG.info("out {}",outCity);
        }
        return outCity;
    }

    /**Below handler methods are for the filter options of
     ** the Staff Users(InActive) **/
    @RequestMapping(value = "/bannedstaffTable", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, Object>> FilteredStaffBlocked(@RequestParam("designation") String designation, @RequestParam("department")
                                                    String department, @RequestParam("branch") String branch,
                                                    @RequestParam("name") String name){


        List<Map<String, Object>> outStaffb = new ArrayList<Map<String, Object>>();

        //query for designation select filtering
        if(!(designation.equals("--Select--"))) {
            List<Map<String, Object>> outStaff1 = new ArrayList<Map<String, Object>>();
            List<User> staffList1 = staffRepository.filterByDesignation(designation,"inactive");

            for (User staffUser : staffList1) {
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
                outStaff1.add(map);
                LOG.info("out {}", outStaff1);
            }
            LOG.error("Filter by designation {}", designation);
            return outStaff1;
        }
        //query for department select filtering
        if(!(department.equals("--Select--"))) {
            List<Map<String, Object>> outStaff2 = new ArrayList<Map<String, Object>>();
            List<User> staffList2 = staffRepository.filterByDepartment(department,"inactive");

            for (User staffUser : staffList2) {
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
                outStaff2.add(map);
                LOG.info("out {}", outStaff2);
            }
            LOG.error("Filter by department {}", department);
            return outStaff2;
        }

        //query for branch select filtering
        if(!(branch.equals("--Select--"))) {
            List<Map<String, Object>> outStaff3 = new ArrayList<Map<String, Object>>();
            List<User> staffList3 = staffRepository.filterByBranch(branch,"inactive");

            for (User staffUser : staffList3) {
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
                outStaff3.add(map);
                LOG.info("out {}", outStaff3);
            }
            LOG.error("Filter by branch {}", branch);
            return outStaff3;
        }
        return outStaffb;
    }



}
