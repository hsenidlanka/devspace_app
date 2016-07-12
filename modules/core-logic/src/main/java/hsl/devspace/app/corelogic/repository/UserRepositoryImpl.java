package hsl.devspace.app.corelogic.repository;

import hsl.devspace.app.corelogic.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;

/**
 * Created by hsenid on 7/4/16.
 */
public class UserRepositoryImpl implements UserRepository{
    User user=new User();
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;

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
try {
    String sql = "INSERT INTO users " +
            "(username,password) VALUES (?,?)";


    jdbcTemplate.update(sql, new Object[]{user.getUsername(), user.getPassword()});
    transactionManager.commit(stat);
}
        catch (Exception SQLIntegrityConstraintViolationException ){
            System.out.println("Duplicate Entry");

        }
    }
    @Override
    public void deleteUser(String username) {
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);
        user.setUsername(username);

        String sql = "DELETE FROM users WHERE username = ?";
        jdbcTemplate.update(sql, new Object[]{ user.getUsername() });
        transactionManager.commit(stat);


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
    }

    @Override
    public boolean confirmPassword() {
        return user.isConfirmed();
    }



}
