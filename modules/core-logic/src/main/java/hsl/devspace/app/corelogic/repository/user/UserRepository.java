package hsl.devspace.app.corelogic.repository.user;

import hsl.devspace.app.corelogic.domain.User;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.TransientDataAccessResourceException;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLType;
import java.util.List;

/**
 * Created by hsenid on 7/4/16.
 */
public interface UserRepository {


    public int add(User user) throws SQLIntegrityConstraintViolationException, DuplicateKeyException;

    int delete(String username) throws SQLException;

    void changePassword(String username, String password, String nPassw) throws SQLException;

    boolean confirmPassword() throws SQLException;

    int loginAuthenticate(String username,String password) throws SQLException;

    int modify(User user) throws SQLException, TransientDataAccessResourceException;

    User retrieveSelectedUserDetails(String username);
    List<User> selectAll();

    int block(String username);

    int unblock(String username);
    public int updateGroupStaff(String des,String username);
    List<User> retrieveCustomersByDate(java.sql.Date date);
    List<User> retrieveByDateRange(java.sql.Date date1,java.sql.Date date2);
    List<User>filter(SQLType column,String filterValue);
    int countUsers();
    boolean checkUsernameUnique(User user);
    List<User> selectActiveUsers();
    List<User> selectBlockedUsers();



}