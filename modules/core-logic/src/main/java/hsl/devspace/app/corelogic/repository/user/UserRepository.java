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

    int loginAuthenticate(String username, String password) throws SQLException;


    int update(User user) throws SQLException, TransientDataAccessResourceException;

    User retrieveSelectedUserDetails(String username);

    List<User> selectAll(int limit, int page);

    int block(String username);

    int unblock(String username);

    public int updateGroupStaff(String des, String username);

    List<User> retrieveCustomersByDate(java.sql.Date date, int limit, int page);

    List<User> retrieveByDateRange(java.sql.Date date1, java.sql.Date date2, int limit, int page);

    public List<User> retrieveByDateRangeCity(Date date1, Date date2,String city, int limit, int page) ;

    List<User> filterByCity(String city, String status, int limit, int page);

    int countUsers(String status);

    boolean checkUsernameUnique(User user);

    List<User> selectActiveUsers(int limit, int page);

    List<User> selectBlockedUsers(int limit, int page);

    int addStaffMember(User user);

    int deleteStaff(String username);

    int updateStaffMember(User user);

    List<User> selectbyEndingDate(Date date, int limit, int page);

    List<User> selectbyStartingDate(Date date, int limit, int page);

    List<User> filterByDepartment(String department, String status, int limit, int page);

    List<User> filterByDesignation(String designation, String status, int limit, int page);

    public List<User> filterByDepartmentDesig(String department, String designation, String status, int limit, int page);

    public List<User> filterByBranchDesigDep(String branch,String designation,String department, String status, int limit, int page);

    public List<User> filterByBranchDesig(String branch, String designation, String status, int limit, int page) ;

    public List<User> filterByBranchDep(String branch, String department, String status, int limit, int page);

    List<User> filterByBranch(String branch, String status, int limit, int page);

    List<User> filterBlockedUsersByCity(String city, int limit, int page);

    List<User> selectAllByNameTypeAhead(String nameKey, String status, int limit, int page);

    List<String> selectNameByNameTypeAhead(String nameKey, String status);

    int countByDate(Date date);

    int countByDateRange(Date date1, Date date2);

    int countByCity(String city, String status);

    int countActiveUsers();

    int countBlockedUsers();

    int countByEndingDate(Date date);

    int countByStartingDate(Date date);

    int countBlockedUsersByCity(String city);

    int countAllByNameTypeAhead(String nameKey, String status);

    int countByDesignation(String designation, String status);

    int countByDepartment(String department, String status);

    int countByDepartmentDesig(String department, String designation, String status);

    int countByBranch(String branch, String status);

    int addVerificationCode(String username, String code);

    void changeStatusToActiveFromNotVerified(String username, String code);


}