package hsl.devspace.app.corelogic.repository.user;

import hsl.devspace.app.corelogic.domain.User;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.TransientDataAccessResourceException;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * Created by hsenid on 7/4/16.
 */
public interface UserRepository {


    public int add(User user) throws SQLIntegrityConstraintViolationException, DuplicateKeyException;

    int delete(String username) throws SQLException;

    int changePassword(String username, String password, String nPassw) throws SQLException;

    boolean confirmPassword() throws SQLException;

    int loginAuthenticate(String username,String password) throws SQLException;

    int update(User user) throws SQLException, TransientDataAccessResourceException;

    User retrieveSelectedUserDetails(String username);
    List<User> selectAll();

    int block(String username);

    int unblock(String username);
    public int updateGroupStaff(String des,String username);
    List<User> retrieveCustomersByDate(java.sql.Date date);
    List<User> retrieveByDateRange(java.sql.Date date1,java.sql.Date date2);
    List<User>filterByCity(String city,String status);
    int countUsers();
    boolean checkUsernameUnique(User user);
    List<User> selectActiveUsers();
    List<User> selectBlockedUsers();
    int addStaffMember(User user);
    int deleteStaff(String username);
    int updateStaffMember(User user);
    List<User> selectbyEndingDate(Date date);
    List<User> selectbyStartingDate(Date date);
    List<User> filterByDepartment(String department,String status);
    List<User> filterByDesignation(String designation,String status);
    public List<User> filterByDepartmentDesig(String department,String designation,String status);
    List<User> filterByBranch(String branch,String status);
    List<User>filterBlockedUsersByCity(String city);

    List<User> selectAllByNameTypeAhead(String nameKey, String status);



}