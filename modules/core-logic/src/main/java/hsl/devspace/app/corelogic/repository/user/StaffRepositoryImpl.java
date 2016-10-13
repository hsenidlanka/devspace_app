package hsl.devspace.app.corelogic.repository.user;

import hsl.devspace.app.corelogic.domain.User;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hsenid on 9/8/16.
 */
public class StaffRepositoryImpl implements UserRepository {
    User user = new User();
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;
   // private static org.apache.log4j.Logger log = Logger.getLogger(UserRepositoryImpl.class);
   org.slf4j.Logger log = LoggerFactory.getLogger(StaffRepositoryImpl.class);


    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);

    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;

    }

    /*add a new staff member*/
    @Override
    public int add(User user) throws DuplicateKeyException {

        int row = 0;

        String un = user.getUsername();
        String pw = user.getPassword();
        String des = user.getDesignation();
        if (un != "" && pw != "" ) {
           // if (checkUsernameUnique(un)==true) {


                String sql = "INSERT INTO staff " +
                        "(title,username,password,first_name,last_name,email,mobile,address_line1,address_line2,address_line3," +
                        "designation,department,branch,register_date,status) VALUES (?,?,sha1(?),?,?,?,?,?,?,?,?,?,?,CURRENT_DATE,1)";

                row = jdbcTemplate.update(sql, new Object[]{user.getTitle(), user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(),
                        user.getEmail(), user.getMobile(), user.getAddressL1(), user.getAddressL2(), user.getAddressL3(), user.getDesignation(),
                        user.getDepartment(), user.getBranch()});
                updateGroupStaff(des, un);


                log.info("{} staff inserted",row);

            /*}else
                log.info("username already available");*/
        } else
            log.error("values cannot be empty");

        return row;

    }

    /*insert into group staff table when adding a new staff member*/
    @Override
    public int updateGroupStaff(String des,String username) {

        int row;
    List<Map<String, Object>> mp1 = jdbcTemplate.queryForList("SELECT id FROM staff WHERE username=?",username);

    List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT id FROM `group` WHERE name =?",des);

    String sql = "INSERT INTO group_staff " +
                "(group_id,staff_id) VALUES (?,?)";

        row = jdbcTemplate.update(sql,new Object[]{mp.get(0).get("id"),mp1.get(0).get("id")});
        log.info("{} group-staff updated",row);
        return row;

    }

    /*delete a staff member*/
    @Override
    public int delete(String username) throws IllegalArgumentException {

        user.setUsername(username);

        String sql = "DELETE FROM staff WHERE username = ?";
        int row = jdbcTemplate.update(sql, new Object[]{user.getUsername()});
        log.info("{} staff deleted",row);
        return row;

    }


    /*change password of a existing staff user*/
    @Override
    public void changePassword(String username, String password, String nPw) {


        user.setUsername(username);
        user.setPassword(password);
        int verified = loginAuthenticate(username, password);
        if (verified==1) {

            user.setPassword(nPw);
            System.out.println(user.getPassword());

            String sql = "UPDATE staff SET password = sha1(?) WHERE username = ? ";
            int row = jdbcTemplate.update(sql, new Object[]{user.getPassword(), user.getUsername()});
            log.info("{} password changed",row);
        } else log.info("cannot change password");
    }

    @Override
    public boolean confirmPassword() {
        return user.isConfirmed();
    }

    /*verify whether password and username matched for a specific user*/
    //blocked=2
    //credentials matched=1
    //mismatched=0
    @Override
    public int loginAuthenticate(String username, String password) {
        int result ;
        List<Map<String, Object>> mp1= jdbcTemplate.queryForList("SELECT status FROM staff WHERE BINARY username = ?",username);
        log.info("{}",mp1.get(0).get("status"));
        if (mp1.get(0).get("status").toString().equals("active")) {
            List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM staff WHERE BINARY username = ? AND BINARY password =sha1(?)", username, password);
            log.info("{}",mp);

            if (mp.size() != 0) {
                log.info("{}",mp.get(0));
                result = 1;
            }
            else result=0;

        }
        else result=2;
        log.info("{}",result);

        return result;
    }

    /*update username and password for a specific user*/
    @Override
    public int modify(User user) throws TransientDataAccessResourceException, SQLException {

        String sql = "UPDATE staff SET username=? password = ? WHERE username = ? ";

        int count = jdbcTemplate.update(sql, new Object[]{user.getUsername(), user.getPassword(), (user.getUsername())}, Integer.class);
        log.info("{}",count);
        return count;

    }

    /*retrieve all details for a specific staff user*/
    @Override
    public List<User> retrieveSelectedUserDetails(String username) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM staff WHERE BINARY username = ?", username);
        List<User> staffDetails=new ArrayList<User>();

        for (int i=0;i<mp.size();i++){
            User staff = new User();
            staff.setId(Integer.parseInt(mp.get(i).get("id").toString()));
            staff.setTitle(mp.get(i).get("title").toString());
            staff.setUsername(mp.get(i).get("username").toString());
            staff.setPassword(mp.get(i).get("password").toString());
            staff.setFirstName(mp.get(i).get("first_name").toString());
            staff.setLastName(mp.get(i).get("last_name").toString());
            staff.setEmail(mp.get(i).get("email").toString());
            staff.setMobile(mp.get(i).get("mobile").toString());
            staff.setAddressL1(mp.get(i).get("address_line1").toString());
            staff.setAddressL2(mp.get(i).get("address_line2").toString());
            staff.setAddressL3(mp.get(i).get("address_line3").toString());
            staff.setDesignation(mp.get(i).get("designation").toString());
            staff.setDepartment(mp.get(i).get("department").toString());
            staff.setBranch(mp.get(i).get("branch").toString());
            staff.setRegDate(Date.valueOf(mp.get(i).get("register_date").toString()));
            staff.setStatus(mp.get(i).get("status").toString());
            staffDetails.add(staff);


        }
        log.info("{}",staffDetails);
        return staffDetails;
    }

    /*retrieve details of all staff users*/
    @Override
    public List<User> selectAll() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM staff");
        List<User> staffDetails=new ArrayList<User>();

        for (int i=0;i<mp.size();i++){
            User staff = new User();
            staff.setId(Integer.parseInt(mp.get(i).get("id").toString()));
            staff.setTitle(mp.get(i).get("title").toString());
            staff.setUsername(mp.get(i).get("username").toString());
            staff.setPassword(mp.get(i).get("password").toString());
            staff.setFirstName(mp.get(i).get("first_name").toString());
            staff.setLastName(mp.get(i).get("last_name").toString());
            staff.setEmail(mp.get(i).get("email").toString());
            staff.setMobile(mp.get(i).get("mobile").toString());
            staff.setAddressL1(mp.get(i).get("address_line1").toString());
            staff.setAddressL2(mp.get(i).get("address_line2").toString());
            staff.setAddressL3(mp.get(i).get("address_line3").toString());
            staff.setDesignation(mp.get(i).get("designation").toString());
            staff.setDepartment(mp.get(i).get("department").toString());
            staff.setBranch(mp.get(i).get("branch").toString());
            staff.setRegDate(Date.valueOf(mp.get(i).get("register_date").toString()));
            staff.setStatus(mp.get(i).get("status").toString());
            staffDetails.add(staff);


        }
        log.info("{}",staffDetails);
        return staffDetails;
    }

    /*block a certain staff member*/
    @Override
    public int block(String username) {

        user.setUsername(username);

        String sql = "UPDATE staff SET status=2 WHERE username = ?";
        int row = jdbcTemplate.update(sql, new Object[]{user.getUsername()});
        log.info("{} block status",row);
        return row;
    }

    /*unblock a blocked staff user*/
    @Override
    public int unblock(String username) {

        user.setUsername(username);

        String sql = "UPDATE staff SET status=1 WHERE username = ?";
        int row = jdbcTemplate.update(sql, new Object[]{user.getUsername()});
        log.info("{} unblock status",row);
        return row;
    }

    /*retrieve details of staff members registered on a specific date*/
    @Override
    public List<User> retrieveCustomersByDate(java.sql.Date date) {

        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM staff WHERE register_date = ?", date);
        List<User> staffDetails=new ArrayList<User>();

        for (int i=0;i<mp.size();i++){
            User staff = new User();
            staff.setId(Integer.parseInt(mp.get(i).get("id").toString()));
            staff.setTitle(mp.get(i).get("title").toString());
            staff.setUsername(mp.get(i).get("username").toString());
            staff.setPassword(mp.get(i).get("password").toString());
            staff.setFirstName(mp.get(i).get("first_name").toString());
            staff.setLastName(mp.get(i).get("last_name").toString());
            staff.setEmail(mp.get(i).get("email").toString());
            staff.setMobile(mp.get(i).get("mobile").toString());
            staff.setAddressL1(mp.get(i).get("address_line1").toString());
            staff.setAddressL2(mp.get(i).get("address_line2").toString());
            staff.setAddressL3(mp.get(i).get("address_line3").toString());
            staff.setDesignation(mp.get(i).get("designation").toString());
            staff.setDepartment(mp.get(i).get("department").toString());
            staff.setBranch(mp.get(i).get("branch").toString());
            staff.setRegDate(Date.valueOf(mp.get(i).get("register_date").toString()));
            staff.setStatus(mp.get(i).get("status").toString());
            staffDetails.add(staff);


        }
        log.info("{}",staffDetails);
        return staffDetails;
    }

/*
    retrieve details of staff members registered between specified time period
*/
    @Override
    public List<User> retrieveByDateRange(Date date1, Date date2) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM staff WHERE register_date BETWEEN ? AND ?", date1, date2);
        List<User> staffDetails=new ArrayList<User>();

        for (int i=0;i<mp.size();i++){
            User staff = new User();
            staff.setId(Integer.parseInt(mp.get(i).get("id").toString()));
            staff.setTitle(mp.get(i).get("title").toString());
            staff.setUsername(mp.get(i).get("username").toString());
            staff.setPassword(mp.get(i).get("password").toString());
            staff.setFirstName(mp.get(i).get("first_name").toString());
            staff.setLastName(mp.get(i).get("last_name").toString());
            staff.setEmail(mp.get(i).get("email").toString());
            staff.setMobile(mp.get(i).get("mobile").toString());
            staff.setAddressL1(mp.get(i).get("address_line1").toString());
            staff.setAddressL2(mp.get(i).get("address_line2").toString());
            staff.setAddressL3(mp.get(i).get("address_line3").toString());
            staff.setDesignation(mp.get(i).get("designation").toString());
            staff.setDepartment(mp.get(i).get("department").toString());
            staff.setBranch(mp.get(i).get("branch").toString());
            staff.setRegDate(Date.valueOf(mp.get(i).get("register_date").toString()));
            staff.setStatus(mp.get(i).get("status").toString());
            staffDetails.add(staff);
        }
        log.info("{}",staffDetails);
        return staffDetails;
    }

    /*retrieve details of staff members filtered by a given attribute*/
    @Override
    public List<User> filter(SQLType column, String filterValue) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM staff WHERE ? = ?", column, filterValue);
        List<User> staffDetails=new ArrayList<User>();

        for (int i=0;i<mp.size();i++){
            User staff = new User();
            staff.setId(Integer.parseInt(mp.get(i).get("id").toString()));
            staff.setTitle(mp.get(i).get("title").toString());
            staff.setUsername(mp.get(i).get("username").toString());
            staff.setPassword(mp.get(i).get("password").toString());
            staff.setFirstName(mp.get(i).get("first_name").toString());
            staff.setLastName(mp.get(i).get("last_name").toString());
            staff.setEmail(mp.get(i).get("email").toString());
            staff.setMobile(mp.get(i).get("mobile").toString());
            staff.setAddressL1(mp.get(i).get("address_line1").toString());
            staff.setAddressL2(mp.get(i).get("address_line2").toString());
            staff.setAddressL3(mp.get(i).get("address_line3").toString());
            staff.setDesignation(mp.get(i).get("designation").toString());
            staff.setDepartment(mp.get(i).get("department").toString());
            staff.setBranch(mp.get(i).get("branch").toString());
            staff.setRegDate(Date.valueOf(mp.get(i).get("register_date").toString()));
            staff.setStatus(mp.get(i).get("status").toString());
            staffDetails.add(staff);


        }
        log.info("{}",staffDetails);
        return staffDetails;

    }

    /*retrieve the total no.of staff members*/
    @Override
    public int countUsers() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM staff ");
        int count = mp.size();
        log.info("{}",count);
        return count;
    }

    /*check username availability*/
    @Override
    public boolean checkUsernameUnique(User user) {

        boolean result = true;

        String sql = "SELECT count(*) FROM staff WHERE  username = ? ";

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
    public List<User> selectActiveUsers() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM staff WHERE status=1");
        List<User> staffDetails=new ArrayList<User>();

        for (int i=0;i<mp.size();i++){
            User staff = new User();
            staff.setId(Integer.parseInt(mp.get(i).get("id").toString()));
            staff.setTitle(mp.get(i).get("title").toString());
            staff.setUsername(mp.get(i).get("username").toString());
            staff.setPassword(mp.get(i).get("password").toString());
            staff.setFirstName(mp.get(i).get("first_name").toString());
            staff.setLastName(mp.get(i).get("last_name").toString());
            staff.setEmail(mp.get(i).get("email").toString());
            staff.setMobile(mp.get(i).get("mobile").toString());
            staff.setAddressL1(mp.get(i).get("address_line1").toString());
            staff.setAddressL2(mp.get(i).get("address_line2").toString());
            staff.setAddressL3(mp.get(i).get("address_line3").toString());
            staff.setDesignation(mp.get(i).get("designation").toString());
            staff.setDepartment(mp.get(i).get("department").toString());
            staff.setBranch(mp.get(i).get("branch").toString());
            staff.setRegDate(Date.valueOf(mp.get(i).get("register_date").toString()));
            staff.setStatus(mp.get(i).get("status").toString());
            staffDetails.add(staff);


        }
        log.info("{}",staffDetails);
        return staffDetails;
    }

    @Override
    public List<User> selectBlockedUsers() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM staff WHERE status=2");
        List<User> staffDetails=new ArrayList<User>();

        for (int i=0;i<mp.size();i++){
            User staff = new User();
            staff.setId(Integer.parseInt(mp.get(i).get("id").toString()));
            staff.setTitle(mp.get(i).get("title").toString());
            staff.setUsername(mp.get(i).get("username").toString());
            staff.setPassword(mp.get(i).get("password").toString());
            staff.setFirstName(mp.get(i).get("first_name").toString());
            staff.setLastName(mp.get(i).get("last_name").toString());
            staff.setEmail(mp.get(i).get("email").toString());
            staff.setMobile(mp.get(i).get("mobile").toString());
            staff.setAddressL1(mp.get(i).get("address_line1").toString());
            staff.setAddressL2(mp.get(i).get("address_line2").toString());
            staff.setAddressL3(mp.get(i).get("address_line3").toString());
            staff.setDesignation(mp.get(i).get("designation").toString());
            staff.setDepartment(mp.get(i).get("department").toString());
            staff.setBranch(mp.get(i).get("branch").toString());
            staff.setRegDate(Date.valueOf(mp.get(i).get("register_date").toString()));
            staff.setStatus(mp.get(i).get("status").toString());
            staffDetails.add(staff);


        }
        log.info("{}",staffDetails);
        return staffDetails;
    }

    public void test(String des){
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT id FROM `group` ");
        log.info("{}",mp);

    }

    /*add a new staff member*/
    public void addStaffMember(User user) {
        TransactionDefinition trDef = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(trDef);
        String des=user.getDesignation();
        String username=user.getUsername();
        try {
            log.info("going updated");

            add(user);
           // updateGroupStaff( des, username);
            log.info("updated");
            transactionManager.commit(stat);
        } catch (Exception e) {
            transactionManager.rollback(stat);
            log.info("rollbacked");
        }

    }

    /*add a new staff member*/
    @Transactional
    public void addStaffMember2(User user) {
        add(user);
       // updateGroupStaff(user);
    }

    }






