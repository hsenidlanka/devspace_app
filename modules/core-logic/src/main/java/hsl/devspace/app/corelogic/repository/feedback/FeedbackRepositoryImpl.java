package hsl.devspace.app.corelogic.repository.feedback;

import hsl.devspace.app.corelogic.domain.Feedback;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

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
                "(date,time,comment,number_of_stars,customer_id,item_id,status) VALUES (CURDATE(),CURTIME(),?,?,(SELECT id FROM customer WHERE username=?),(SELECT id FROM item WHERE name=?),'inactive')";

        row = jdbcTemplate.update(sql, new Object[]{feedback.getComment(),feedback.getNumberOfStars(),feedback.getCustomerUserName(),feedback.getItemName()});
        log.info("{} new feedback added",row);
        return row;
    }

    /*retrieve feedback details*/
    @Override
    public List<Map<String, Object>> view(int limit, int page) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT f.*,c.username, i.name AS item FROM feedback f INNER JOIN item i ON i.id=f.item_id INNER JOIN customer c ON f.customer_id=c.id ORDER BY f.id DESC LIMIT ? OFFSET ?", limit, page);
        log.info("{}", mp);
        return mp;
    }

    @Override
    public int changeStatus(int id, String status) {
        String sql = "UPDATE feedback SET status=? WHERE id=?";
        int row = jdbcTemplate.update(sql, new Object[]{status, id});
        return row;
    }

    @Override
    public List<Map<String, Object>> selectBlockedFeedbacks(int limit, int offset) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT f.*,c.username, i.name AS item FROM feedback f INNER JOIN item i ON i.id=f.item_id INNER JOIN customer c ON f.customer_id=c.id WHERE f.status='block' ORDER BY f.id DESC LIMIT ? OFFSET ? ", limit, offset);
        return mp;
    }

    @Override
    public List<Map<String, Object>> selectActiveFeedbacks(int limit, int offset) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT f.*,c.username, i.name AS item FROM feedback f INNER JOIN item i ON i.id=f.item_id INNER JOIN customer c ON f.customer_id=c.id WHERE f.status='active' ORDER BY f.id DESC LIMIT ? OFFSET ? ", limit, offset);
        System.out.println(mp);
        return mp;
    }

    @Override
    public List<Map<String, Object>> selectInactiveFeedbacks(int limit, int offset) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT f.*,c.username, i.name AS item FROM feedback f INNER JOIN item i ON i.id=f.item_id INNER JOIN customer c ON f.customer_id=c.id WHERE f.status='inactive' ORDER BY f.id DESC LIMIT ? OFFSET ? ", limit, offset);
        System.out.println(mp);

        return mp;
    }

    @Override
    public List<Map<String, Object>> selectFeedbacksByDateRange(java.sql.Date dateFrom, java.sql.Date dateTo, int limit, int offset) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT f.*,c.username, i.name AS item FROM feedback f INNER JOIN item i ON i.id=f.item_id INNER JOIN customer c ON f.customer_id=c.id WHERE f.date BETWEEN ? AND ? ORDER BY f.id DESC LIMIT ? OFFSET ? ", dateFrom, dateTo, limit, offset);
        System.out.println(mp);

        return mp;
    }

    @Override
    public List<Map<String, Object>> selectFeedbacksByDateRangeAndStatus(java.sql.Date dateFrom, java.sql.Date dateTo, String status, int limit, int offset) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT f.*,c.username, i.name AS item FROM feedback f INNER JOIN item i ON i.id=f.item_id INNER JOIN customer c ON f.customer_id=c.id WHERE f.date BETWEEN ? AND ? AND f.status=? ORDER BY f.id DESC LIMIT ? OFFSET ? ", dateFrom, dateTo, status, limit, offset);
        System.out.println(mp);

        return mp;
    }
}
