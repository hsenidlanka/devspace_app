package hsl.devspace.app.corelogic.repository.group;

import hsl.devspace.app.corelogic.domain.Group;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by hsenid on 9/22/16.
 */
public class GroupRepositoryImpl implements GroupRepository {
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;
    //private static org.apache.log4j.Logger log = Logger.getLogger(GroupRepositoryImpl.class);
    org.slf4j.Logger log = LoggerFactory.getLogger(GroupRepositoryImpl.class);

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);

    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;

    }

    /*Add new user group*/
    @Override
    public int add(Group group) {
        int row;
        String sql = "INSERT INTO `group` " +
                    "(name,description,module_id) VALUES (?,?,(SELECT id FROM MODULE WHERE name=?))";

        row = jdbcTemplate.update(sql, new Object[]{group.getModuleName(),group.getDescription(),group.getModuleName()});
        log.info( "{} new group inserted" ,row);
        return row;
    }

    /*delete a specific user group*/
    @Override
    public int delete(String groupName) {
        String sql = "DELETE FROM `group` WHERE name = ?";
        int row = jdbcTemplate.update(sql, new Object[]{groupName});
        log.info(" {} group deleted",row);
        return row;
    }
}
