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

    @RequestMapping(value = "/customerTable", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, Object>> dateFilteredStaff(@RequestParam("from") String from, @RequestParam("to") String to,
                                                @RequestParam("name") String name, @RequestParam("city") String city)
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
        LOG.error("city {}", city);

        List<Map<String, Object>> outc = new ArrayList<Map<String, Object>>();

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
                outc.add(map);
                LOG.info("out {}",outc);
            }
        }
        if(!city.equals("--Select--")){
//            List<User> customerList2= customerRepository.filter()
        }
        return outc ;
    }



}
