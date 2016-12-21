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


//////////////////////////////////////////////////////////////  ACTIVE USER Filter options handle //////////////////////////////////////////


    /** handler  method to filter the customer user data based on typeahead usernames **/
    @RequestMapping(value = "/customerTable/typeheadName/data", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, Object>> typeheadNameFilterData (@RequestParam("cname") String cname,
                                                      @RequestParam("pageLimit") String pageLimit,
                                                      @RequestParam("initPage") String initPage){
        int initPg = Integer.parseInt(initPage);
        int limitPg = Integer.parseInt(pageLimit);

        List<Map<String, Object>> nameList=nameSelect(cname,"active",limitPg,initPg);
        return  nameList;
    }



    /** handler  method to filter the customer usernames based on typeahead usernames **/
    @RequestMapping(value = "/customerTable/typeheadName", method = RequestMethod.GET)
    public @ResponseBody
    List<String> typeheadNameFilter (@RequestParam("cname") String cname){
        List<String> nameList=customerRepository.selectNameByNameTypeAhead(cname, "active");
        return  nameList;
    }


    /** handler  method to filter the customer user data based on typeahead usernames **/
    @RequestMapping(value = "/staffTable/typeheadName/data", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, Object>> typeheadNameFilterDataStaff (@RequestParam("sname") String sname,
                                                           @RequestParam("pageLimit") String pageLimit,
                                                           @RequestParam("initPage") String initPage){
        int initPg = Integer.parseInt(initPage);
        int limitPg = Integer.parseInt(pageLimit);

        List<Map<String, Object>> nameList=nameSelectStaff(sname, "active",limitPg,initPg);
        return  nameList;
    }

    /** handler  method to filter the customer usernames based on typeahead usernames **/
    @RequestMapping(value = "/staffTable/typeheadName", method = RequestMethod.GET)
    public @ResponseBody
    List<String> typeheadNameFilterStaff (@RequestParam("sname") String sname){
        List<String> nameList=staffRepository.selectNameByNameTypeAhead(sname, "active");
        return  nameList;
    }



    /** handler  method to filter
     * the search data on the registered date**/
    @RequestMapping(value = "/customerTable/date", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, Object>> dateFilteredCustomer(@RequestParam("from") String from, @RequestParam("to") String to,
                                                @RequestParam("name") String name,
                                                @RequestParam("pageLimit") String pageLimit,
                                                @RequestParam("initPage") String initPage)
                                                throws ParseException {

        int initPg = Integer.parseInt(initPage);
        int limitPg = Integer.parseInt(pageLimit);

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
            List<User> customerList1= customerRepository.retrieveByDateRange(sqlfromDate,sqltoDate,limitPg,initPg);

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
     * the search data of active customers on the city**/
    @RequestMapping(value = "/customerTable/city", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, Object>> cityFilteredCustomer( @RequestParam("city") String city,
                                                    @RequestParam("pageLimit") String pageLimit,
                                                    @RequestParam("initPage") String initPage) {

        int initPg = Integer.parseInt(initPage);
        int limitPg = Integer.parseInt(pageLimit);

             LOG.error("city {}", city);
             List<Map<String, Object>> outCity = new ArrayList<Map<String, Object>>();
             outCity=citySelect(city,"active",limitPg,initPg);
             return outCity;
         }

    /** handler method to filter
     * the search data on the registered date and the city**/
    @RequestMapping(value = "/customerTable/date/city", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, Object>> datecityFilteredCustomer(@RequestParam("from") String from, @RequestParam("to") String to,
                                                        @RequestParam("city") String city,
                                                        @RequestParam("pageLimit") String pageLimit,
                                                        @RequestParam("initPage") String initPage) throws ParseException {

              int initPg = Integer.parseInt(initPage);
              int limitPg = Integer.parseInt(pageLimit);

              //convert java.util time to sql time
              SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy");
              Date fromDate= sdf.parse(from);
              Date toDate= sdf.parse(to);
              java.sql.Date sqlfromDate= new java.sql.Date(fromDate.getTime());
              java.sql.Date sqltoDate= new java.sql.Date(toDate.getTime());


              ArrayList<Map<String, Object>> outCityDate1 = new ArrayList<Map<String, Object>>();
              ArrayList<Map<String, Object>> outCityDate2 = new ArrayList<Map<String, Object>>();

        //two lists of User objects obtained filtered by date and city respectively
              List<User> customerList3=customerRepository.retrieveByDateRange(sqlfromDate,sqltoDate,limitPg,initPg);
              List<User> customerList4=customerRepository.filterByCity(city,"active",limitPg,initPg);

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
    List<Map<String, Object>> FilteredStaff(@RequestParam("designation") String designation,
                                            @RequestParam("department") String department,
                                            @RequestParam("branch") String branch,
                                            @RequestParam("name") String name,
                                            @RequestParam("pageLimit") String pageLimit,
                                            @RequestParam("initPage") String initPage){

        int initPg = Integer.parseInt(initPage);
        int limitPg = Integer.parseInt(pageLimit);

        List<Map<String, Object>> outStaff = new ArrayList<Map<String, Object>>();

        //query for designation select filtering
        if((!(designation.equals("--Select--"))) && ((branch.equals("--Select--"))) && ((department.equals("--Select--")))) {
            List<Map<String, Object>> designationList=designationSelect(designation, "active",limitPg,initPg);
            return designationList;

        }
        //query for department select filtering
        if((!(department.equals("--Select--"))) && ((branch.equals("--Select--"))) && ((designation.equals("--Select--")))){
            List<Map<String, Object>> depList=departmentSelect(department,"active",limitPg,initPg);
            return depList;

        }

        //query for branch select filtering
        if((!(branch.equals("--Select--"))) && ((designation.equals("--Select--"))) && ((department.equals("--Select--")))){
            List<Map<String, Object>> branchList=branchSelect(branch,"active",limitPg,initPg);
            return branchList;
        }

        //query for branch and department select filtering
        if((!(branch.equals("--Select--"))) && (!(department.equals("--Select--"))) &&((designation.equals("--Select--")))) {

            List<Map<String, Object>> branchList=branchSelect(branch,"active",limitPg,initPg);
            List<Map<String, Object>> depList=departmentSelect(department, "active",limitPg,initPg);

            //to get the intersection of the two lists outCityDate1 and outCityDate2
            ArrayList<Map<String, Object>> common= new ArrayList<Map<String, Object>>(branchList);
            common.retainAll(depList);
            return common;
        }
        //////query for designation and department select filtering
        if((!(designation.equals("--Select--"))) && (!(department.equals("--Select--"))) && ((branch.equals("--Select--"))) ) {

            List<Map<String, Object>> designationList=designationSelect(designation, "active",limitPg,initPg);
            List<Map<String, Object>> depList=departmentSelect(department, "active",limitPg,initPg);

            //to get the intersection of the two lists outCityDate1 and outCityDate2
            ArrayList<Map<String, Object>> common= new ArrayList<Map<String, Object>>(designationList);
            common.retainAll(depList);
            return common;
        }
        ///////query for designation and branch select filtering
        if((!(designation.equals("--Select--"))) && ((department.equals("--Select--"))) && (!(branch.equals("--Select--"))) ) {

            List<Map<String, Object>> designationList=designationSelect(designation,"active",limitPg,initPg);
            List<Map<String, Object>> branchList=branchSelect(branch,"active",limitPg,initPg);

            //to get the intersection of the two lists outCityDate1 and outCityDate2
            ArrayList<Map<String, Object>> common= new ArrayList<Map<String, Object>>(designationList);
            common.retainAll(branchList);
            return common;
        }

        //query for designation and branch and department select filtering
        if((!(designation.equals("--Select--"))) && (!(department.equals("--Select--"))) && (!(branch.equals("--Select--"))) ) {

            List<Map<String, Object>> designationDepList=designationDepSelect(department,designation,"active",limitPg,initPg);
            List<Map<String, Object>> branchList=branchSelect(branch,"active",limitPg,initPg);

            //to get the intersection of the two lists outCityDate1 and outCityDate2
            ArrayList<Map<String, Object>> common= new ArrayList<Map<String, Object>>(designationDepList);
            common.retainAll(branchList);

            return common;
        }
        return outStaff;
    }

///////////////////////////////////////////////////////////  INACTIVE USER Filter options handling ///////////////////////////////////////////

    /**Below handler methods are for the filter options of
     ** the Customer Users(InActive) **/

    /** handler  method to filter the banned customer user data based on typeahead usernames **/
    @RequestMapping(value = "/bannedCustomerTable/typeheadName/data", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, Object>> typeheadBannedNameFilterData (@RequestParam("bcname") String bcname,
                                                            @RequestParam("pageLimit") String pageLimit,
                                                            @RequestParam("initPage") String initPage){
        int initPg = Integer.parseInt(initPage);
        int limitPg = Integer.parseInt(pageLimit);

        List<Map<String, Object>> nameListB=nameSelect(bcname,"inactive",limitPg,initPg);
        return  nameListB;
    }

    /** handler  method to filter the  banned customer usernames based on typeahead usernames **/
    @RequestMapping(value = "/bannedCustomerTable/typeheadName", method = RequestMethod.GET)
    public @ResponseBody
    List<String> typeheadBannedNameFilter (@RequestParam("bcname") String bcname){
        List<String> nameListB=customerRepository.selectNameByNameTypeAhead(bcname, "inactive");
        return  nameListB;
    }

    /** handler  method to filter the customer user data based on typeahead usernames **/
    @RequestMapping(value = "/bannedStaffTable/typeheadName/data", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, Object>> typeheadBannedNameFilterDataStaff (@RequestParam("bsname") String bsname,
                                                                 @RequestParam("pageLimit") String pageLimit,
                                                                 @RequestParam("initPage") String initPage){
        int initPg = Integer.parseInt(initPage);
        int limitPg = Integer.parseInt(pageLimit);

        List<Map<String, Object>> nameListB=nameSelectStaff(bsname, "inactive",limitPg,initPg);
        return  nameListB;
    }

    /** handler  method to filter the customer usernames based on typeahead usernames **/
    @RequestMapping(value = "/bannedStaffTable/typeheadName", method = RequestMethod.GET)
    public @ResponseBody
    List<String> typeheadBannedNameFilterStaff (@RequestParam("bsname") String bsname){
        List<String> nameListB=staffRepository.selectNameByNameTypeAhead(bsname, "inactive");
        return  nameListB;
    }

    /** handler method  to filter
     * the search data on the registered date**/
    @RequestMapping(value = "/bannedcustomerTable/city", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, Object>> cityFilteredCustomerBlocked( @RequestParam("city") String city,
                                                           @RequestParam("name") String name,
                                                           @RequestParam("pageLimit") String pageLimit,
                                                           @RequestParam("initPage") String initPage) {
        int initPg = Integer.parseInt(initPage);
        int limitPg = Integer.parseInt(pageLimit);

        LOG.info("city {}", city);
        List<Map<String, Object>> outCustomerb = new ArrayList<Map<String, Object>>();

        if((!(city.equals("--Select--"))) && ((name.equals(""))) ) {
            List<Map<String, Object>> cityList=citySelect(city,"inactive",limitPg,initPg);
            return cityList;
        }
        if(((city.equals("--Select--"))) && (!(name.equals(""))) ) {
            List<Map<String, Object>> nameList=nameSelect(name,"inactive",limitPg,initPg);
            return nameList;
        }
        if((!(city.equals("--Select--"))) && (!(name.equals(""))) ) {
            List<Map<String, Object>> cityList=citySelect(city,"inactive",limitPg,initPg);
            List<Map<String, Object>> nameList=nameSelect(name,"inactive",limitPg,initPg);

            //to get the intersection of the two lists outCityDate1 and outCityDate2
            ArrayList<Map<String, Object>> common= new ArrayList<Map<String, Object>>(cityList);
            common.retainAll(nameList);
            return common;
        }
        return  outCustomerb;
    }

    /**Below handler methods are for the filter options of
     ** the Staff Users(InActive) **/
    @RequestMapping(value = "/bannedstaffTable", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, Object>> FilteredStaffBlocked(@RequestParam("designation") String designation, @RequestParam("department")
                                                    String department, @RequestParam("branch") String branch,
                                                    @RequestParam("name") String name, @RequestParam("pageLimit") String pageLimit,
                                                    @RequestParam("initPage") String initPage){
        int initPg = Integer.parseInt(initPage);
        int limitPg = Integer.parseInt(pageLimit);

        List<Map<String, Object>> outStaffb = new ArrayList<Map<String, Object>>();

        //query for designation select filtering
        if((!(designation.equals("--Select--"))) && ((branch.equals("--Select--"))) && ((department.equals("--Select--")))) {
            List<Map<String, Object>> designationList=designationSelect(designation, "inactive",limitPg,initPg);
            return designationList;

        }
        //query for department select filtering
        if((!(department.equals("--Select--"))) && ((branch.equals("--Select--"))) && ((designation.equals("--Select--")))){
            List<Map<String, Object>> depList=departmentSelect(department,"inactive",limitPg,initPg);
            return depList;

        }

        //query for branch select filtering
        if((!(branch.equals("--Select--"))) && ((designation.equals("--Select--"))) && ((department.equals("--Select--")))){
            List<Map<String, Object>> branchList=branchSelect(branch,"inactive",limitPg,initPg);
            return branchList;
        }

        //query for branch and department select filtering
        if((!(branch.equals("--Select--"))) && (!(department.equals("--Select--"))) &&((designation.equals("--Select--")))) {

            List<Map<String, Object>> branchList=branchSelect(branch,"inactive",limitPg,initPg);
            List<Map<String, Object>> depList=departmentSelect(department, "inactive",limitPg,initPg);

            //to get the intersection of the two lists outCityDate1 and outCityDate2
            ArrayList<Map<String, Object>> common= new ArrayList<Map<String, Object>>(branchList);
            common.retainAll(depList);
            return common;
        }
        //query for designation and department select filtering
        if((!(designation.equals("--Select--"))) && (!(department.equals("--Select--"))) && ((branch.equals("--Select--"))) ) {

            List<Map<String, Object>> designationList=designationSelect(designation, "inactive",limitPg,initPg);
            List<Map<String, Object>> depList=departmentSelect(department, "inactive",limitPg,initPg);

            //to get the intersection of the two lists outCityDate1 and outCityDate2
            ArrayList<Map<String, Object>> common= new ArrayList<Map<String, Object>>(designationList);
            common.retainAll(depList);
            return common;
        }
        //query for designation and branch select filtering
        if((!(designation.equals("--Select--"))) && ((department.equals("--Select--"))) && (!(branch.equals("--Select--"))) ) {

            List<Map<String, Object>> designationList=designationSelect(designation,"inactive",limitPg,initPg);
            List<Map<String, Object>> branchList=branchSelect(branch,"inactive",limitPg,initPg);

            //to get the intersection of the two lists outCityDate1 and outCityDate2
            ArrayList<Map<String, Object>> common= new ArrayList<Map<String, Object>>(designationList);
            common.retainAll(branchList);
            return common;
        }

        //query for designation and branch select filtering
        if((!(designation.equals("--Select--"))) && (!(department.equals("--Select--"))) && (!(branch.equals("--Select--"))) ) {

            List<Map<String, Object>> designationDepList=designationDepSelect(department,designation,"inactive",limitPg,initPg);
            List<Map<String, Object>> branchList=branchSelect(branch,"inactive",limitPg,initPg);

            //to get the intersection of the two lists outCityDate1 and outCityDate2
            ArrayList<Map<String, Object>> common= new ArrayList<Map<String, Object>>(designationDepList);
            common.retainAll(branchList);

            return common;
        }
        return outStaffb;
    }

/////////////////////////////////////////////// METHODS FOR FILTERINGS OF STAFF USERS ///////////////////////////////////////////////////////////////////////

    //method for filtering database values based on the designation
    public List<Map<String, Object>> designationSelect(String designation, String status,int limit, int page ){
        List<Map<String, Object>> outStaff1 = new ArrayList<Map<String, Object>>();
        List<User> staffList1 = staffRepository.filterByDesignation(designation,status,limit,page);

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

    //method for filtering database values based on the designation and department
    public List<Map<String, Object>> designationDepSelect(String department,String designation, String status,int limit, int page){
        List<Map<String, Object>> outStaff1 = new ArrayList<Map<String, Object>>();
        List<User> staffList1 = staffRepository.filterByDepartmentDesig(department, designation, status,limit,page);

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
        LOG.info("Filter by designation {}", designation);
        return outStaff1;
    }


    public List<Map<String, Object>> branchSelect(String branch, String status,int limit, int page){
        List<Map<String, Object>> outStaff3 = new ArrayList<Map<String, Object>>();
        List<User> staffList3 = staffRepository.filterByBranch(branch,status,limit,page);

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

    public List<Map<String, Object>> departmentSelect(String department, String status,int limit, int page){
        List<Map<String, Object>> outStaff2 = new ArrayList<Map<String, Object>>();
        List<User> staffList2 = staffRepository.filterByDepartment(department,status,limit,page);

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

    public List<Map<String, Object>> citySelect(String city, String status, int limit, int page) {
        List<Map<String, Object>> outCity = new ArrayList<Map<String, Object>>();
        List<User> customerList2 = customerRepository.filterByCity(city,status,limit,page);

        for (int i = 0; i < customerList2.size(); i++) {
            User customerUser = customerList2.get(i);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", customerUser.getId());
            map.put("username", customerUser.getUsername());
            map.put("first_name", customerUser.getFirstName());
            map.put("last_name", customerUser.getLastName());
            map.put("mobile", customerUser.getMobile());
            map.put("email", customerUser.getEmail());
            map.put("address_line3", customerUser.getAddressL3());
            map.put("registered_date", customerUser.getRegDate());

            LOG.info("newUser {}", customerUser);
            outCity.add(map);
            LOG.info("out {}", outCity);
        }
        return outCity;
    }

    //function to obtain the Customer List with typed name
    public List<Map<String, Object>> nameSelect(String name, String status, int limit, int page) {
        List<Map<String, Object>> outNameList = new ArrayList<Map<String, Object>>();
        List<User> customerList2 = customerRepository.selectAllByNameTypeAhead(name,status,limit,page);
        for (int i = 0; i < customerList2.size(); i++) {
            User customerUser = customerList2.get(i);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", customerUser.getId());
            map.put("username", customerUser.getUsername());
            map.put("first_name", customerUser.getFirstName());
            map.put("last_name", customerUser.getLastName());
            map.put("mobile", customerUser.getMobile());
            map.put("email", customerUser.getEmail());
            map.put("address_line3", customerUser.getAddressL3());
            map.put("registered_date", customerUser.getRegDate());

            LOG.info("newUser {}", customerUser);
            outNameList.add(map);
           LOG.info("out {}", outNameList);
        }
        return outNameList;
    }

    //function to obtain the Staff List with typed name
    public List<Map<String, Object>> nameSelectStaff(String name, String status,int limit,int page) {
        List<Map<String, Object>> outNameList = new ArrayList<Map<String, Object>>();
        List<User> staffList2 = staffRepository.selectAllByNameTypeAhead(name,status,limit,page);

        for (int i = 0; i < staffList2.size(); i++) {
            User staffUser = staffList2.get(i);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", staffUser.getId());
            map.put("username", staffUser.getUsername());
            map.put("first_name", staffUser.getFirstName());
            map.put("mobile", staffUser.getMobile());
            map.put("designation", staffUser.getDesignation());
            map.put("department", staffUser.getDepartment());
            map.put("branch", staffUser.getBranch());
            LOG.info("newUser {}", staffUser);
            outNameList.add(map);
            LOG.info("out {}", outNameList);
        }
       return outNameList;
    }


}
