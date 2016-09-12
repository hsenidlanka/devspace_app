package hsl.devspace.app.corelogic.repository;

import hsl.devspace.app.corelogic.domain.User;
import org.apache.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by hsenid on 7/4/16.
 */
public class UserRepositoryImpl implements UserRepository {
    User user = new User();
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;
    private static org.apache.log4j.Logger log = Logger.getLogger(UserRepositoryImpl.class);

    public void setDataSource(DataSource dataSource) {
        //this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);

    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;

    }


    @Override
    public int add(User user) throws DuplicateKeyException {
        int row = 0;
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);
        String un = user.getUsername();
        String pw = user.getPassword();
        if (un != "" && pw != "") {
            String sql = "INSERT INTO customer " +
                    "(title,first_name,last_name,username,password,email,address_line1,address_line2,address_line3,mobile,registered_date,status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

            row = jdbcTemplate.update(sql, new Object[]{user.getTitle(), user.getFirstName(),user.getLastName(),user.getUsername(),user.getPassword(),
            user.getEmail(),user.getAddressL1(),user.getAddressL2(),user.getCity(),user.getMobile(),"2016-11-11","active"});
            transactionManager.commit(stat);

            log.info(row + "inserted");
            log.info(un);
        } else
            log.error("values cannot be null");

        return row;

    }

    @Override
    public int delete(String username) throws IllegalArgumentException {
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);
        user.setUsername(username);

        String sql = "DELETE FROM customer WHERE username = ?";
        int row = jdbcTemplate.update(sql, new Object[]{user.getUsername()});
        transactionManager.commit(stat);
        log.info(row + "deleted");
        return row;

    }


    @Override
    public void changePassword(String username, String password, String nPw) {
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);

        user.setUsername(username);
        user.setPassword(password);
        boolean verified = loginAuthenticate(user);
        if (verified) {

            user.setPassword(nPw);
            System.out.println(user.getPassword());

            String sql = "UPDATE customer SET password = ? WHERE username = ? ";
            int row = jdbcTemplate.update(sql, new Object[]{user.getPassword(), user.getUsername()});
            transactionManager.rollback(stat);
            log.info(row + "password changed");
        } else log.info("cannot");
    }

    @Override
    public boolean confirmPassword() {
        return user.isConfirmed();
    }

    @Override
    public boolean loginAuthenticate(User user) {
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);

        String sql = "SELECT count(*) FROM customer WHERE BINARY username = ? AND BINARY password = ? ";
        boolean result = false;

        int count = jdbcTemplate.queryForObject(
                sql, new Object[]{user.getUsername(), (user.getPassword())}, Integer.class);

        if (count > 0) {
            result = true;
        }
        transactionManager.rollback(stat);
        log.info(result);
        return result;
    }

    @Override
    public int modify(User user) throws TransientDataAccessResourceException, SQLException {
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);
        String sql = "UPDATE users SET username=? password = ? WHERE username = ? ";
        /*int count = jdbcTemplate.queryForObject(
                sql, new Object[]{user.getUsername(), user.getPassword(), (user.getUsername())}, Integer.class);
       */
        int count = jdbcTemplate.update(sql, new Object[]{user.getUsername(), user.getPassword(), (user.getUsername())}, Integer.class);
        transactionManager.commit(stat);
        log.info(count);
        return count;

    }

    @Override
    public List<Map<String, Object>> retrieveMultipleRowsColumns(String username) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM customer WHERE BINARY username = ?", username);
        log.info(mp);
        return mp;
    }


}

