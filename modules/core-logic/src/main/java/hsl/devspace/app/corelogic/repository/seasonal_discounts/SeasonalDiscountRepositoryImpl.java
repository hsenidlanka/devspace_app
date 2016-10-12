package hsl.devspace.app.corelogic.repository.seasonal_discounts;

import hsl.devspace.app.corelogic.domain.SeasonalDiscount;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.Date;

/**
 * Created by hsenid on 9/20/16.
 */
public class SeasonalDiscountRepositoryImpl implements SeasonalDiscountRepository{
SeasonalDiscount sd=new SeasonalDiscount();
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;
  //  private static org.apache.log4j.Logger log = Logger.getLogger(SeasonalDiscountRepositoryImpl.class);
  org.slf4j.Logger log = LoggerFactory.getLogger(SeasonalDiscountRepositoryImpl.class);

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);

    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;

    }

    /*Add new seasonal discount*/
    @Override
    public int add(SeasonalDiscount seasonalDiscount) {
        int row;
        String sql = "INSERT INTO seasonal_discount " +
                "(rate,start_date,expire_date,status) VALUES (?,?,?,1)";

        row = jdbcTemplate.update(sql, new Object[]{seasonalDiscount.getRate(),seasonalDiscount.getStartDate(),seasonalDiscount.getExpireDate()});
        log.info("{} new seasonal discount added",row);
        return row;
    }

    /*delete a seasonal discount*/
    @Override
    public int delete(int id) {
        String sql = "DELETE FROM seasonal_discount WHERE id = ?";
        int row = jdbcTemplate.update(sql, new Object[]{id});
        log.info("{} Seasonal discount deleted",row);
        return row;
    }

    /*change status to inactive for expired seasonal discounts*/
    @Override
    public int inactiveStatus() {

        String sql = "UPDATE seasonal_discount SET status=2 WHERE expire_date < CURRENT_DATE ";
        int row = jdbcTemplate.update(sql);
        log.info("{} inactive discount after expired",row);
        return row;
    }

    /*change expire date of a specific seasonal discount*/
    @Override
    public int changeExpireDate(int id, Date exp) {

        String sql = "UPDATE seasonal_discount SET expire_date=? WHERE id = ?";
        int row = jdbcTemplate.update(sql, new Object[]{exp,id});
        log.info("{} change expire date",row);
        return row;
    }

    /*change status to inactive*/
    @Override
    public int inactiveStatus(int id) {

        String sql = "UPDATE seasonal_discount SET status=2 WHERE id=?";
        int row = jdbcTemplate.update(sql, new Object[]{id});
        log.info("{} inactive status manually");
        return row;
    }

}
