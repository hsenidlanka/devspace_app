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
        this.dataSource = dataSource;
    }
    public void setTransactionManager(PlatformTransactionManager transactionManager){
        this.transactionManager=transactionManager;
    }
    @Override
    public void deleteUser(String username) {
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);
        user.setUsername(username);
        String sql = "DELETE FROM users WHERE username = ?";

        jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.update(sql, new Object[]{ user.getUsername() });
        transactionManager.commit(stat);
    }

    @Override
    public String changePassword() {
        return user.getPassword();
    }

    @Override
    public boolean confirmPassword() {
        return user.isConfirmed();
    }
}
