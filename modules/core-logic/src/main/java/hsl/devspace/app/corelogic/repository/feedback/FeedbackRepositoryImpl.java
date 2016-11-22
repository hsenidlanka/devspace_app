package hsl.devspace.app.corelogic.repository.feedback;

import hsl.devspace.app.corelogic.domain.Feedback;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by hsenid on 9/19/16.
 */
public class FeedbackRepositoryImpl implements FeedbackRepository{
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;
    //private static org.apache.log4j.Logger log = Logger.getLogger(FeedbackRepositoryImpl.class);
    org.slf4j.Logger log = LoggerFactory.getLogger(FeedbackRepositoryImpl.class);

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);

    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;

    }

    /*Add new feedback*/
    @Override
    public int add(Feedback feedback) {
        int row;
        String sql = "INSERT INTO feedback " +
                "(date,time,comment,number_of_stars,customer_id,item_id) VALUES (CURDATE(),CURTIME(),?,?,(SELECT id FROM customer WHERE username=?),(SELECT id FROM item WHERE name=?))";

        row = jdbcTemplate.update(sql, new Object[]{feedback.getComment(),feedback.getNumberOfStars(),feedback.getCustomerUserName(),feedback.getItemName()});
        log.info("{} new feedback added",row);
        return row;
    }
}
