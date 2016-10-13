package hsl.devspace.app.corelogic.repository.permission;

import hsl.devspace.app.corelogic.domain.Permission;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by hsenid on 9/22/16.
 */
public class PermissionRepositoryImpl implements PermissionRepository{
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;
   // private static org.apache.log4j.Logger log = Logger.getLogger(PermissionRepositoryImpl.class);
    org.slf4j.Logger log = LoggerFactory.getLogger(PermissionRepositoryImpl.class);

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);

    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;

    }

    /*Add new permission*/
    @Override
    public int add(Permission permission) {
        int row;
        String sql = "INSERT INTO permission " +
                "(name,description) VALUES (?,?)";

        row = jdbcTemplate.update(sql, new Object[]{permission.getPermissionName(),permission.getDescription()});
        log.info("{} new permission inserted",row);
        return row;
    }

    /*delete a specific permission*/
    @Override
    public int delete(String permissionName) {
        String sql = "DELETE FROM permission WHERE name = ?";
        int row = jdbcTemplate.update(sql, new Object[]{permissionName});
        log.info("{} permission deleted",row);
        return row;
    }
}
