package hsl.devspace.app.admin.usermanagement.spring.controller;

import hsl.devspace.app.corelogic.domain.User;
import hsl.devspace.app.corelogic.repository.user.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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


    /** handler method to filter
     * the search data of Customer Users on the registered date range and the city combination**/
    @RequestMapping(value = "/customerTable/date/city", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, Object>> datecityFilteredCustomer(@RequestParam("from") String from,
                                                       @RequestParam("to") String to,
                                                        @RequestParam("city") String city,
                                                        @RequestParam("pageLimit") String pageLimit,
                                                        @RequestParam("initPage") String initPage) throws ParseException {

        int initPg = Integer.parseInt(initPage);
        int limitPg = Integer.parseInt(pageLimit);

        List<Map<String, Object>> outCustomer = new ArrayList<Map<String, Object>>();

        //date range is selected
        if((!(from.equals(""))) && (!(to.equals(""))) && ((city.equals("--Select--")))) {

            LOG.error("date range selected: from{}, to{}",from,to);
            List<Map<String, Object>> outDateRange=dateSelect(from,to,"active",limitPg,initPg);
            return outDateRange;
        }

        //city is selected
        if(((from.equals(""))) && ((to.equals(""))) && (!(city.equals("--Select--")))) {

            LOG.error("city {}", city);
            List<Map<String, Object>> outCity = citySelect(city, "active", limitPg, initPg);
            return outCity;
        }

        //both the city and date range are selected
        if((!(from.equals(""))) && (!(to.equals(""))) && (!(city.equals("--Select--")))) {

            LOG.error(" Date range and city selected: city{}, from:{}, to{}", city,from,to);
            List<Map<String, Object>> outCityDate=dateCitySelect(from,to,city,"active",limitPg,initPg);
            return outCityDate;
        }

     return outCustomer;
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
        //////query for designation and department select filtering
        if((!(designation.equals("--Select--"))) && (!(department.equals("--Select--"))) && ((branch.equals("--Select--"))) ) {
            List<Map<String, Object>> designationDepList=designationDepSelect(department,designation,"active",limitPg,initPg);
            return designationDepList;
        }
        //query for branch and department select filtering
        if((!(branch.equals("--Select--"))) && (!(department.equals("--Select--"))) &&((designation.equals("--Select--")))) {

            List<Map<String, Object>> branchDepList=branchDepSelect(branch, department, "active", limitPg, initPg);
            return branchDepList;
        }

        ///////query for designation and branch select filtering
        if((!(designation.equals("--Select--"))) && ((department.equals("--Select--"))) && (!(branch.equals("--Select--"))) ) {

            List<Map<String, Object>> branchDesigList=branchDesigSelect(branch, designation, "active", limitPg, initPg);
            return branchDesigList;
        }

        //query for designation and branch and department select filtering
        if((!(designation.equals("--Select--"))) && (!(department.equals("--Select--"))) && (!(branch.equals("--Select--"))) ) {

            List<Map<String, Object>> branchDesigDepList=branchDesigDepSelect(branch, designation, department, "active", limitPg, initPg);
            return branchDesigDepList;
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
            List<Map<String, Object>> branchDepList=branchDepSelect(branch, department, "inactive", limitPg, initPg);
            return branchDepList;
        }
        //query for designation and department select filtering
        if((!(designation.equals("--Select--"))) && (!(department.equals("--Select--"))) && ((branch.equals("--Select--"))) ) {
            List<Map<String, Object>> designationDepList=designationDepSelect(department, designation, "inactive", limitPg, initPg);
            return designationDepList;
        }
        //query for designation and branch select filtering
        if((!(designation.equals("--Select--"))) && ((department.equals("--Select--"))) && (!(branch.equals("--Select--"))) ) {

            List<Map<String, Object>> branchDesigList=branchDesigSelect(branch, designation, "inactive", limitPg, initPg);
            return branchDesigList;
        }

        //query for designation and branch select filtering
        if((!(designation.equals("--Select--"))) && (!(department.equals("--Select--"))) && (!(branch.equals("--Select--"))) ) {

            List<Map<String, Object>> branchDesigDepList=branchDesigDepSelect(branch, designation, department, "inactive", limitPg, initPg);
            return branchDesigDepList;
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
    public List<Map<String, Object>> branchDesigDepSelect(String branch,String designation,String department, String status,int limit, int page){
        List<Map<String, Object>> outStaff1 = new ArrayList<Map<String, Object>>();
        List<User> staffList1 = staffRepository.filterByBranchDesigDep(branch, designation, department, status,limit,page);

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

    //method for filtering database values based on the designation and department
    public List<Map<String, Object>> branchDepSelect(String branch, String department,String status,int limit, int page){
        List<Map<String, Object>> outStaff1 = new ArrayList<Map<String, Object>>();
        List<User> staffList1 = staffRepository.filterByBranchDep(branch, department, status,limit,page);

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
        return outStaff1;
    }


    //method for filtering Staff database values based on the designation and branch
    public List<Map<String, Object>> branchDesigSelect(String branch,String designation, String status,int limit, int page){
        List<Map<String, Object>> outStaff1 = new ArrayList<Map<String, Object>>();
        List<User> staffList1 = staffRepository.filterByBranchDesig(branch, designation, status, limit, page);

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


    public List<Map<String, Object>> dateSelect(String from,String to,String status, int limitPg, int initPg) throws ParseException {

        //convert java.util time to sql time
        SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy");
        Date fromDate= sdf.parse(from);
        Date toDate= sdf.parse(to);
        java.sql.Date sqlfromDate= new java.sql.Date(fromDate.getTime());
        java.sql.Date sqltoDate= new java.sql.Date(toDate.getTime());

        List<Map<String, Object>> outDate = new ArrayList<Map<String, Object>>();
        List<User> customerList1= customerRepository.retrieveByDateRange(sqlfromDate,sqltoDate,status,limitPg,initPg);

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
        return outDate;
    }

    public List<Map<String, Object>> dateCitySelect(String from,String to, String city,String status, int limitPg, int initPg)
            throws ParseException {

        //convert java.util time to sql time
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date fromDate = sdf.parse(from);
        Date toDate = sdf.parse(to);
        java.sql.Date sqlfromDate = new java.sql.Date(fromDate.getTime());
        java.sql.Date sqltoDate = new java.sql.Date(toDate.getTime());
        LOG.error("sqlFromDate: {}", sqlfromDate);
        LOG.error("city in dateCitySelect:{}",city);

        //two lists of User objects obtained filtered by date and city respectively
        List<User> dateFilteredcustomerList = customerRepository.retrieveByDateRangeCity(sqlfromDate, sqltoDate, city, status,limitPg, initPg);
        LOG.error("retrive by date range {}", dateFilteredcustomerList);
        ArrayList<Map<String, Object>> outCityDate1 = new ArrayList<Map<String, Object>>();
        //first filter by date range
        for (int i = 0; i < dateFilteredcustomerList.size(); i++) {
            User customerUser = dateFilteredcustomerList.get(i);
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
            outCityDate1.add(map);
            LOG.error("out {}", outCityDate1);
        }
        return outCityDate1;
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
//////////////////////////////////////////////////to filter NON-Verified users from Active Users//////////////////////////////

    /*
*getting record count for loading Customer table with pagination (NOT-VERIFIED)
**/
    @RequestMapping(value = "/NonVerifiedCustomerPaginationTable", method = RequestMethod.GET)
    public @ResponseBody int loadPagination2(){
        LOG.info("Category Count is {}",customerRepository.countUsers("not-verified"));
        return customerRepository.countUsers("not-verified");
    }


    //handler method to retrieve the details of a particular NON-vERIFIED  customer user
    @RequestMapping(value = "/view/customerTable/nonVerified", method = RequestMethod.GET)
    public @ResponseBody List<Map<String, Object>> viewCustomer(@ModelAttribute("newUser")  User customerUser,
                                                                @RequestParam("pageLimit") String pageLimit,
                                                                @RequestParam("initPage") String initPage){

        int initPg = Integer.parseInt(initPage);
        int limitPg = Integer.parseInt(pageLimit);

        List<Map<String, Object>> outc = new ArrayList<Map<String, Object>>();
        List<User> customerList= customerRepository.selectNonVerifiedUsers(limitPg, initPg);

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

    /** handler  method to filter the NON-VERIFIED customer user data based on typeahead usernames **/
    @RequestMapping(value = "/customerTable/typeheadName/data/nonVerified", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, Object>> typeheadNameFilterDataNonVerified (@RequestParam("cname") String cname,
                                                      @RequestParam("pageLimit") String pageLimit,
                                                      @RequestParam("initPage") String initPage){
        int initPg = Integer.parseInt(initPage);
        int limitPg = Integer.parseInt(pageLimit);

        List<Map<String, Object>> nameListTypeaheadDataNon=nameSelect(cname,"not-verified",limitPg,initPg);
        return  nameListTypeaheadDataNon;
    }



    /** handler  method to filter the customer usernames based on typeahead usernames **/
    @RequestMapping(value = "/customerTable/typeheadName/nonVerified", method = RequestMethod.GET)
    public @ResponseBody
    List<String> typeheadNameFilterNonVerified (@RequestParam("cname") String cname){
        List<String> nameListTypeaheadNon=customerRepository.selectNameByNameTypeAhead(cname, "not-verified");
        return  nameListTypeaheadNon;
    }
//////////////////////////////////////NON-VERIFIED Customers Filter by date range and city///////////////////////////////////

    /** handler method to filter
     * the search data of Customer Users on the registered date range and the city combination**/
    @RequestMapping(value = "/customerTable/date/city/nonVerified", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, Object>> datecityFilteredNonVerifiedCustomer(@RequestParam("from") String from,
                                                       @RequestParam("to") String to,
                                                       @RequestParam("city") String city,
                                                       @RequestParam("pageLimit") String pageLimit,
                                                       @RequestParam("initPage") String initPage) throws ParseException {

        int initPg = Integer.parseInt(initPage);
        int limitPg = Integer.parseInt(pageLimit);

        List<Map<String, Object>> outCustomerNonVerified = new ArrayList<Map<String, Object>>();

        //date range is selected
        if((!(from.equals(""))) && (!(to.equals(""))) && ((city.equals("--Select--")))) {

            LOG.error("date range selected: from{}, to{}",from,to);
            List<Map<String, Object>> outDateRangeN=dateSelect(from,to,"not-verified",limitPg,initPg);
            return outDateRangeN;
        }

        //city is selected
        if(((from.equals(""))) && ((to.equals(""))) && (!(city.equals("--Select--")))) {

            LOG.error("city {}", city);
            List<Map<String, Object>> outCityN = citySelect(city, "not-verified", limitPg, initPg);
            return outCityN;
        }

        //both the city and date range are selected
        if((!(from.equals(""))) && (!(to.equals(""))) && (!(city.equals("--Select--")))) {

            LOG.error(" Date range and city selected: city{}, from:{}, to{}", city,from,to);
            List<Map<String, Object>> outCityDateN=dateCitySelect(from,to,city,"not-verified",limitPg,initPg);
            return outCityDateN;
        }

        return outCustomerNonVerified;
    }


}
