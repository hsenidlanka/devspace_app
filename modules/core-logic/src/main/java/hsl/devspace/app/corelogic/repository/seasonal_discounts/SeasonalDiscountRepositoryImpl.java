package hsl.devspace.app.corelogic.repository.seasonal_discounts;

import hsl.devspace.app.corelogic.domain.SeasonalDiscount;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;

/**
 * Created by hsenid on 9/20/16.
 */
public class SeasonalDiscountRepositoryImpl implements SeasonalDiscountRepository{
SeasonalDiscount sd=new SeasonalDiscount();
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;
    private static org.apache.log4j.Logger log = Logger.getLogger(SeasonalDiscountRepositoryImpl.class);

    public void setDataSource(DataSource dataSource) {
        //this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);

    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;

    }

    @Override
    public int add(SeasonalDiscount seasonalDiscount) {
        int row;
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);
        String sql = "INSERT INTO seasonal_discount " +
                "(rate,start_date,expire_date,status) VALUES (?,?,?,active)";

        row = jdbcTemplate.update(sql, new Object[]{seasonalDiscount.getRate(),seasonalDiscount.getStartDate(),seasonalDiscount.getExpireDate()});
        transactionManager.commit(stat);
        log.info(row + "new seasonal discount added");
        return row;
    }

    @Override
    public int delete(int id) {
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);

        String sql = "DELETE FROM seasonal_discount WHERE id = ?";
        int row = jdbcTemplate.update(sql, new Object[]{id});
        transactionManager.commit(stat);
        log.info(row + " Seasonal discount deleted");
        return row;
    }

}
