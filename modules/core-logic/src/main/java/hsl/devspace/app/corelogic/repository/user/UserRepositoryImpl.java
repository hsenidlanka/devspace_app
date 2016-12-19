package hsl.devspace.app.corelogic.repository.user;

import hsl.devspace.app.corelogic.domain.User;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hsenid on 7/4/16.
 */
public  class UserRepositoryImpl implements UserRepository {
    User user = new User();
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;
    //private static org.apache.log4j.Logger log = Logger.getLogger(UserRepositoryImpl.class);
    org.slf4j.Logger log = LoggerFactory.getLogger(UserRepositoryImpl.class);


    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);

    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    /*add a new customer*/
    @Override
    public int add(User user) throws DuplicateKeyException {
        int row = 0;
        String un = user.getUsername();
        String pw = user.getPassword();
        if (un != "" && pw != "") {
           // if (checkUsernameUnique(un)==true) {
                String sql = "INSERT INTO customer " +
                        "(title,first_name,last_name,username,password,email,address_line1,address_line2,address_line3,mobile,registered_date,status) VALUES (?,?,?,?,sha1(?),?,?,?,?,?,CURRENT_DATE,1)";

                row = jdbcTemplate.update(sql, new Object[]{user.getTitle(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(),
                        user.getEmail(), user.getAddressL1(), user.getAddressL2(), user.getAddressL3(), user.getMobile()});

                log.info("customer inserted");
          /*  }else
                log.info("username already available");*/
        } else
            log.error("values cannot be empty");

        return row;

    }

    /*delete a customer*/
    @Override
    public int delete(String username) throws IllegalArgumentException {

       // user.setUsername(username);
        String sql = "DELETE FROM customer WHERE username = ?";
        int row = jdbcTemplate.update(sql, new Object[]{username});
        log.info("deleted");
        return row;

    }


    /*change password of a specific customer*/
    @Override
    public int changePassword(String username, String password, String nPw) {
        int row=0;
        user.setUsername(username);
        user.setPassword(password);
        int verified = loginAuthenticate(username,password);
        if (verified==1) {
            user.setPassword(nPw);
            System.out.println(user.getPassword());

            String sql = "UPDATE customer SET password = sha1(?) WHERE username = ? ";
             row = jdbcTemplate.update(sql, new Object[]{user.getPassword(), user.getUsername()});
            log.info(row + "password changed");
        } else log.info("cannot change password");
        return row;
    }

    @Override
    public boolean confirmPassword() {
        return user.isConfirmed();
    }

    /**
     * authenticate username and password matched for a existing customer
       blocked=2
       credentials matched=1
       mismatched=0
    */
    @Override
    public int loginAuthenticate(String username,String password) {

        int result ;
        List<Map<String, Object>> mp1= jdbcTemplate.queryForList("SELECT status FROM customer WHERE BINARY username = ?",username);
        if(mp1.size()!=0) {
            log.info("{}", mp1.get(0).get("status"));
            if (mp1.get(0).get("status").toString().equals("active")) {
                List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM customer WHERE BINARY username = ? AND BINARY password =sha1(?)", username, password);
                log.info("{}", mp);

                if (mp.size() != 0) {
                    log.info("{}", mp.get(0));
                    result = 1;
                } else result = 0;

            } else result = 2;
        }else result=0;
        log.info("{}",result);

        return result;
    }

    /*change username and password for a customer*/
    @Override
    public int update(User user) throws TransientDataAccessResourceException, SQLException {

        String sql = "UPDATE customer SET title=?, first_name=?, last_name=? ,email=? ,address_line1=? ,address_line2=?, address_line3=?, mobile=? WHERE username = ? ";
        int count = jdbcTemplate.update(sql, new Object[]{user.getTitle(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAddressL1(), user.getAddressL2(), user.getAddressL3(), user.getMobile(), user.getUsername()});
        log.info("{}",count);
        return count;

    }

    /*retrieve details for a specific customer*/
    @Override
    public User retrieveSelectedUserDetails(String username) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM customer WHERE BINARY username = ?", username);
        User customer = new User();

        for (int i=0;i<mp.size();i++){
            customer.setId(Integer.parseInt(mp.get(i).get("id").toString()));
            customer.setTitle(mp.get(i).get("title").toString());
            customer.setFirstName(mp.get(i).get("first_name").toString());
            customer.setLastName(mp.get(i).get("last_name").toString());
            customer.setUsername(mp.get(i).get("username").toString());
            customer.setPassword(mp.get(i).get("password").toString());
            customer.setEmail(mp.get(i).get("email").toString());
            customer.setAddressL1(mp.get(i).get("address_line1").toString());
            customer.setAddressL2(mp.get(i).get("address_line2").toString());
            if (mp.get(i).get("address_line3")!=null) {
                customer.setAddressL3(mp.get(i).get("address_line3").toString());
           }
            customer.setMobile(mp.get(i).get("mobile").toString());
            customer.setRegDate(Date.valueOf(mp.get(i).get("registered_date").toString()));
            customer.setStatus(mp.get(i).get("status").toString());



        }
        log.info("{}",customer);
        return customer;
    }

    /*view all customer details*/
    @Override
    public List<User> selectAll(int limit, int page) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM customer LIMIT ? OFFSET ?", limit, page - 1);
        List<User> customerDetails=new ArrayList<User>();

        for (int i=0;i<mp.size();i++){
            User customer = new User();
            customer.setId(Integer.parseInt(mp.get(i).get("id").toString()));
            customer.setTitle(mp.get(i).get("title").toString());
            customer.setFirstName(mp.get(i).get("first_name").toString());
            customer.setLastName(mp.get(i).get("last_name").toString());
            customer.setUsername(mp.get(i).get("username").toString());
            customer.setPassword(mp.get(i).get("password").toString());
            customer.setEmail(mp.get(i).get("email").toString());
            customer.setAddressL1(mp.get(i).get("address_line1").toString());
            customer.setAddressL2(mp.get(i).get("address_line2").toString());
            if (mp.get(i).get("address_line3")!=null) {
                customer.setAddressL3(mp.get(i).get("address_line3").toString());
            }
            customer.setMobile(mp.get(i).get("mobile").toString());
            customer.setRegDate(Date.valueOf(mp.get(i).get("registered_date").toString()));
            customer.setStatus(mp.get(i).get("status").toString());

            customerDetails.add(customer);


        }
        log.info("{}",customerDetails);
        return customerDetails;
    }

    /*block a customer*/
    @Override
    public int block(String username) {

        user.setUsername(username);
        String sql = "UPDATE customer SET status=2 WHERE username = ?";
        int row = jdbcTemplate.update(sql, new Object[]{user.getUsername()});
        log.info("{} block status",row);
        return row;
    }

    /*unblock a blocked customer*/
    @Override
    public int unblock(String username) {

        user.setUsername(username);
        String sql = "UPDATE customer SET status=1 WHERE username = ?";
        int row = jdbcTemplate.update(sql, new Object[]{user.getUsername()});
        log.info("{} unblock status",row);
        return row;
    }

    @Override
    public int updateGroupStaff(String des, String username) {
        return 0;
    }


    /*retrieve details of customer registered on a specific date*/
    @Override
    public List<User> retrieveCustomersByDate(java.sql.Date date, int limit, int page) {

        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM customer WHERE registered_date = ? LIMIT ? OFFSET ?", date, limit, page - 1);
        List<User> customerDetails=new ArrayList<User>();

        for (int i=0;i<mp.size();i++){
            User customer = new User();
            customer.setId(Integer.parseInt(mp.get(i).get("id").toString()));
            customer.setTitle(mp.get(i).get("title").toString());
            customer.setFirstName(mp.get(i).get("first_name").toString());
            customer.setLastName(mp.get(i).get("last_name").toString());
            customer.setUsername(mp.get(i).get("username").toString());
            customer.setPassword(mp.get(i).get("password").toString());
            customer.setEmail(mp.get(i).get("email").toString());
            customer.setAddressL1(mp.get(i).get("address_line1").toString());
            customer.setAddressL2(mp.get(i).get("address_line2").toString());
            if (mp.get(i).get("address_line3")!=null) {
                customer.setAddressL3(mp.get(i).get("address_line3").toString());
            }
            customer.setMobile(mp.get(i).get("mobile").toString());
            customer.setRegDate(Date.valueOf(mp.get(i).get("registered_date").toString()));
            customer.setStatus(mp.get(i).get("status").toString());

            customerDetails.add(customer);


        }
        log.info("{}",customerDetails);
        return customerDetails;
    }

    /*retrieve details of customer registered between a specified date range*/
    @Override
    public List<User> retrieveByDateRange(Date date1, Date date2, int limit, int page) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM customer WHERE registered_date BETWEEN ? AND ? AND status='active' LIMIT ? OFFSET ?", date1, date2, limit, page - 1);
        List<User> customerDetails=new ArrayList<User>();

        for (int i=0;i<mp.size();i++){
            User customer = new User();
            customer.setId(Integer.parseInt(mp.get(i).get("id").toString()));
            customer.setTitle(mp.get(i).get("title").toString());
            customer.setFirstName(mp.get(i).get("first_name").toString());
            customer.setLastName(mp.get(i).get("last_name").toString());
            customer.setUsername(mp.get(i).get("username").toString());
            customer.setPassword(mp.get(i).get("password").toString());
            customer.setEmail(mp.get(i).get("email").toString());
            customer.setAddressL1(mp.get(i).get("address_line1").toString());
            customer.setAddressL2(mp.get(i).get("address_line2").toString());
            if (mp.get(i).get("address_line3")!=null) {
                customer.setAddressL3(mp.get(i).get("address_line3").toString());
            }
            customer.setMobile(mp.get(i).get("mobile").toString());
            customer.setRegDate(Date.valueOf(mp.get(i).get("registered_date").toString()));
            customer.setStatus(mp.get(i).get("status").toString());

            customerDetails.add(customer);


        }
        log.info("{}",customerDetails);
        return customerDetails;
    }

    /*retrieve details of customers by a given attribute*/
    @Override
    public List<User> filterByCity(String city, String status, int limit, int page) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM customer WHERE address_line3 = ? AND status= ? LIMIT ? OFFSET ?", city, status, limit, page - 1);
        List<User> customerDetails=new ArrayList<User>();

        for (int i=0;i<mp.size();i++){
            User customer = new User();
            customer.setId(Integer.parseInt(mp.get(i).get("id").toString()));
            customer.setTitle(mp.get(i).get("title").toString());
            customer.setFirstName(mp.get(i).get("first_name").toString());
            customer.setLastName(mp.get(i).get("last_name").toString());
            customer.setUsername(mp.get(i).get("username").toString());
            customer.setPassword(mp.get(i).get("password").toString());
            customer.setEmail(mp.get(i).get("email").toString()); 
            customer.setAddressL1(mp.get(i).get("address_line1").toString());
            customer.setAddressL2(mp.get(i).get("address_line2").toString());
            if (mp.get(i).get("address_line3")!=null) {
                customer.setAddressL3(mp.get(i).get("address_line3").toString());
            }
            customer.setMobile(mp.get(i).get("mobile").toString());
            customer.setRegDate(Date.valueOf(mp.get(i).get("registered_date").toString()));
            customer.setStatus(mp.get(i).get("status").toString());

            customerDetails.add(customer);


        }
        log.info("{}",customerDetails);
        return customerDetails;

    }

    /*retrieve total no.of customers*/
    @Override
    public int countUsers() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM customer ");
        int count = mp.size();
        log.info("{}",count);
        return count;
    }

    @Override
    public boolean checkUsernameUnique(User user) {

        boolean result = true;

        String sql = "SELECT count(*) FROM customer WHERE  username = ?  ";

        int count = jdbcTemplate.queryForObject(
                sql, new Object[]{user.getUsername()}, Integer.class);

        if (count > 0) {
            result = false;
            log.info("username already available");
        }
        log.info("{}",result);
        return result;
    }

    @Override
    public List<User> selectActiveUsers(int limit, int page) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM customer WHERE status=1LIMIT ? OFFSET ?", limit, page - 1);
        List<User> customerDetails=new ArrayList<User>();

        for (int i=0;i<mp.size();i++){
            User customer = new User();
            customer.setId(Integer.parseInt(mp.get(i).get("id").toString()));
            customer.setTitle(mp.get(i).get("title").toString());
            customer.setFirstName(mp.get(i).get("first_name").toString());
            customer.setLastName(mp.get(i).get("last_name").toString());
            customer.setUsername(mp.get(i).get("username").toString());
            customer.setPassword(mp.get(i).get("password").toString());
            customer.setEmail(mp.get(i).get("email").toString());
            customer.setAddressL1(mp.get(i).get("address_line1").toString());
            customer.setAddressL2(mp.get(i).get("address_line2").toString());
            if (mp.get(i).get("address_line3")!=null) {
                customer.setAddressL3(mp.get(i).get("address_line3").toString());
            }
            customer.setMobile(mp.get(i).get("mobile").toString());
            customer.setRegDate(Date.valueOf(mp.get(i).get("registered_date").toString()));
            customer.setStatus(mp.get(i).get("status").toString());

            customerDetails.add(customer);


        }
        log.info("{}",customerDetails);
        return customerDetails;
    }

    @Override
    public List<User> selectBlockedUsers(int limit, int page) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM customer WHERE status=2 LIMIT ? OFFSET ?", limit, page - 1);
        List<User> customerDetails=new ArrayList<User>();

        for (int i=0;i<mp.size();i++){
            User customer = new User();
            customer.setId(Integer.parseInt(mp.get(i).get("id").toString()));
            customer.setTitle(mp.get(i).get("title").toString());
            customer.setFirstName(mp.get(i).get("first_name").toString());
            customer.setLastName(mp.get(i).get("last_name").toString());
            customer.setUsername(mp.get(i).get("username").toString());
            customer.setPassword(mp.get(i).get("password").toString());
            customer.setEmail(mp.get(i).get("email").toString());
            customer.setAddressL1(mp.get(i).get("address_line1").toString());
            customer.setAddressL2(mp.get(i).get("address_line2").toString());
            if (mp.get(i).get("address_line3")!=null) {
                customer.setAddressL3(mp.get(i).get("address_line3").toString());
            }
            customer.setMobile(mp.get(i).get("mobile").toString());
            customer.setRegDate(Date.valueOf(mp.get(i).get("registered_date").toString()));
            customer.setStatus(mp.get(i).get("status").toString());

            customerDetails.add(customer);
        }
        log.info("{}",customerDetails);
        return customerDetails;
    }

    @Override
    public int addStaffMember(User user) {
        return 0;
    }

    @Override
    public int deleteStaff(String username) {
        return 0;
    }

    @Override
    public int updateStaffMember(User user) {
        return 0;
    }


    @Override
    public List<User> selectbyEndingDate(Date date, int limit, int page) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM customer WHERE registered_date <= ? AND status='active' LIMIT ? OFFSET ?", date, limit, page);
        List<User> customerDetails=new ArrayList<User>();

        for (int i=0;i<mp.size();i++){
            User customer = new User();
            customer.setId(Integer.parseInt(mp.get(i).get("id").toString()));
            customer.setTitle(mp.get(i).get("title").toString());
            customer.setFirstName(mp.get(i).get("first_name").toString());
            customer.setLastName(mp.get(i).get("last_name").toString());
            customer.setUsername(mp.get(i).get("username").toString());
            customer.setPassword(mp.get(i).get("password").toString());
            customer.setEmail(mp.get(i).get("email").toString());
            customer.setAddressL1(mp.get(i).get("address_line1").toString());
            customer.setAddressL2(mp.get(i).get("address_line2").toString());
            if (mp.get(i).get("address_line3")!=null) {
                customer.setAddressL3(mp.get(i).get("address_line3").toString());
            }
            customer.setMobile(mp.get(i).get("mobile").toString());
            customer.setRegDate(Date.valueOf(mp.get(i).get("registered_date").toString()));
            customer.setStatus(mp.get(i).get("status").toString());

            customerDetails.add(customer);
        }
        log.info("{}",customerDetails);
        return customerDetails;    }

    @Override
    public List<User> selectbyStartingDate(Date date, int limit, int page) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM customer WHERE registered_date >= ? AND status='active' LIMIT ? OFFSET ?", date, limit, page - 1);
        List<User> customerDetails=new ArrayList<User>();

        for (int i=0;i<mp.size();i++){
            User customer = new User();
            customer.setId(Integer.parseInt(mp.get(i).get("id").toString()));
            customer.setTitle(mp.get(i).get("title").toString());
            customer.setFirstName(mp.get(i).get("first_name").toString());
            customer.setLastName(mp.get(i).get("last_name").toString());
            customer.setUsername(mp.get(i).get("username").toString());
            customer.setPassword(mp.get(i).get("password").toString());
            customer.setEmail(mp.get(i).get("email").toString());
            customer.setAddressL1(mp.get(i).get("address_line1").toString());
            customer.setAddressL2(mp.get(i).get("address_line2").toString());
            if (mp.get(i).get("address_line3")!=null) {
                customer.setAddressL3(mp.get(i).get("address_line3").toString());
            }
            customer.setMobile(mp.get(i).get("mobile").toString());
            customer.setRegDate(Date.valueOf(mp.get(i).get("registered_date").toString()));
            customer.setStatus(mp.get(i).get("status").toString());

            customerDetails.add(customer);


        }
        log.info("{}",customerDetails);
        return customerDetails;
    }

    @Override
    public List<User> filterByDepartment(String department, String status, int llimit, int page) {
        return null;
    }

    @Override
    public List<User> filterByDesignation(String designation, String status, int llimit, int page) {
        return null;
    }

    @Override
    public List<User> filterByDepartmentDesig(String department, String designation, String status, int llimit, int page) {
        return null;
    }

    @Override
    public List<User> filterByBranch(String branch, String status, int llimit, int page) {
        return null;
    }

    @Override
    public List<User> filterBlockedUsersByCity(String city, int limit, int page) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM customer WHERE address_line3= ? AND status=2 LIMIT ? OFFSET ?", city, limit, page - 1);
        List<User> customerDetails=new ArrayList<User>();

        for (int i=0;i<mp.size();i++){
            User customer = new User();
            customer.setId(Integer.parseInt(mp.get(i).get("id").toString()));
            customer.setTitle(mp.get(i).get("title").toString());
            customer.setFirstName(mp.get(i).get("first_name").toString());
            customer.setLastName(mp.get(i).get("last_name").toString());
            customer.setUsername(mp.get(i).get("username").toString());
            customer.setPassword(mp.get(i).get("password").toString());
            customer.setEmail(mp.get(i).get("email").toString());
            customer.setAddressL1(mp.get(i).get("address_line1").toString());
            customer.setAddressL2(mp.get(i).get("address_line2").toString());
            if (mp.get(i).get("address_line3")!=null) {
                customer.setAddressL3(mp.get(i).get("address_line3").toString());
            }
            customer.setMobile(mp.get(i).get("mobile").toString());
            customer.setRegDate(Date.valueOf(mp.get(i).get("registered_date").toString()));
            customer.setStatus(mp.get(i).get("status").toString());

            customerDetails.add(customer);


        }
        log.info("{}",customerDetails);
        return customerDetails;
    }

    @Override
    public List<User> selectAllByNameTypeAhead(String nameKey, String status, int limit, int page) {
        String key = "%" + nameKey + "%";
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM customer WHERE username LIKE ? AND status = ? LIMIT ? OFFSET ?", key, status, limit, page - 1);
        List<User> customerDetails = new ArrayList<User>();

        for (int i = 0; i < mp.size(); i++) {
            User customer = new User();
            customer.setId(Integer.parseInt(mp.get(i).get("id").toString()));
            customer.setTitle(mp.get(i).get("title").toString());
            customer.setFirstName(mp.get(i).get("first_name").toString());
            customer.setLastName(mp.get(i).get("last_name").toString());
            customer.setUsername(mp.get(i).get("username").toString());
            customer.setPassword(mp.get(i).get("password").toString());
            customer.setEmail(mp.get(i).get("email").toString());
            customer.setAddressL1(mp.get(i).get("address_line1").toString());
            customer.setAddressL2(mp.get(i).get("address_line2").toString());
            if (mp.get(i).get("address_line3") != null) {
                customer.setAddressL3(mp.get(i).get("address_line3").toString());
            }
            customer.setMobile(mp.get(i).get("mobile").toString());
            customer.setRegDate(Date.valueOf(mp.get(i).get("registered_date").toString()));
            customer.setStatus(mp.get(i).get("status").toString());

            customerDetails.add(customer);


        }
        log.info("{}", customerDetails);
        return customerDetails;
    }

    @Override
    public List<String> selectNameByNameTypeAhead(String nameKey, String status, int limit, int page) {
        String key = "%" + nameKey + "%";
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT username FROM customer WHERE username LIKE ? AND status = ? LIMIT ? OFFSET ?", key, status, limit, page - 1);
        List<String> customerDetails = new ArrayList<String>();

        for (int i = 0; i < mp.size(); i++) {
            String a = mp.get(i).get("username").toString();

            customerDetails.add(a);

        }
        log.info("{}", customerDetails);
        return customerDetails;
    }

}
