package hsl.devspace.app.corelogic.repository;

import hsl.devspace.app.corelogic.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.apache.log4j.Logger;
import javax.sql.DataSource;

/**
 * Created by hsenid on 7/4/16.
 */
public class UserRepositoryImpl implements UserRepository{
    User user=new User();
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;
    private static org.apache.log4j.Logger log = Logger.getLogger(UserRepositoryImpl.class);
    public void setDataSource(DataSource dataSource) {
        //this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);

    }
    public void setTransactionManager(PlatformTransactionManager transactionManager){
        this.transactionManager=transactionManager;
    }

    @Override
    public void addUser(User user) {
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);

    String sql = "INSERT INTO users " +
            "(username,password) VALUES (?,?)";


    jdbcTemplate.update(sql, new Object[]{user.getUsername(), user.getPassword()});
    transactionManager.commit(stat);

    }
    @Override
    public void deleteUser(String username) {
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);
        user.setUsername(username);

        String sql = "DELETE FROM users WHERE username = ?";
        jdbcTemplate.update(sql, new Object[]{ user.getUsername() });
        transactionManager.commit(stat);
       // transactionManager.rollback(stat);


    }


    @Override
    public void changePassword(String username,String password) {
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);

        user.setUsername(username);
        user.setPassword(password);
        String sql = "UPDATE users SET password = ? WHERE username = ? ";

        jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.update(sql, new Object[]{ user.getPassword(),user.getUsername() });
        transactionManager.commit(stat);
        //transactionManager.rollback(stat);
    }

    @Override
    public boolean confirmPassword() {
        return user.isConfirmed();
    }

    @Override
    public boolean loginAuthenticate(User user) {
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);

        String sql = "SELECT count(*) FROM users WHERE BINARY username = ? AND BINARY password =? ";
        boolean result = false;

        int count =  jdbcTemplate.queryForObject(
                sql, new Object[]{user.getUsername(), (user.getPassword())}, Integer.class);


        if (count > 0) {
            result = true;
        }
       // transactionManager.commit(stat);
        transactionManager.rollback(stat);
        log.info(result);
        return result;
    }


}
