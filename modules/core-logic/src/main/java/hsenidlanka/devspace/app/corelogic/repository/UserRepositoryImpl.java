package hsenidlanka.devspace.app.corelogic.repository;

import hsenidlanka.devspace.app.corelogic.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by hsenid on 7/4/16.
 */
public class UserRepositoryImpl implements UserRepository{
    User user=new User();
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO users " +
                "(username,password) VALUES (?, ?)";

        jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.update(sql, new Object[] { user.getUsername(),
                user.getPassword() });

    }

    @Override
    public int deleteUser() {
        return user.getUsr_id();

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
