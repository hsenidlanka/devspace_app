package hsl.devspace.app.corelogic.repository;

import hsl.devspace.app.corelogic.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;

/**
 * Created by hsenid on 7/5/16.
 */
public class UserUpdatesImpl implements UserUpdates {
    User user=new User();
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public void setTransactionManager(PlatformTransactionManager transactionManager){
        this.transactionManager=transactionManager;
    }
   /* public TransactionDefinition createTransDef(){
        return new DefaultTransactionDefinition();

    }
    public TransactionStatus createTransStat(TransactionDefinition tr_def){
        return transactionManager.getTransaction(tr_def);
    }*/
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

        //jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.update(sql, new Object[]{ user.getPassword(),user.getUsername() });
        transactionManager.commit(stat);
    }

    @Override
    public boolean confirmPassword() {
        return user.isConfirmed();
    }
}
