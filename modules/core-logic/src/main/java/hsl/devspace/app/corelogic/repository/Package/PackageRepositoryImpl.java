package hsl.devspace.app.corelogic.repository.Package;

import hsl.devspace.app.corelogic.domain.Package;
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
public class PackageRepositoryImpl implements PackageRepository  {
    Package pkg=new Package();
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;
    private static org.apache.log4j.Logger log = Logger.getLogger(PackageRepositoryImpl.class);

    public void setDataSource(DataSource dataSource) {
        //this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);

    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;

    }

    @Override
    public int add(Package pack) {
        int row;
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);
        String sql = "INSERT INTO package " +
                "(name,content,price,image) VALUES (?,?,?,?)";

        row = jdbcTemplate.update(sql, new Object[]{pack.getPackName(),pack.getContent(),pack.getPrice(),pack.getImage()});
        transactionManager.commit(stat);
        log.info(row + "new package added");
        return row;
    }

    @Override
    public int delete(String packageName) {
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);

        String sql = "DELETE FROM package WHERE name = ?";
        int row = jdbcTemplate.update(sql, new Object[]{packageName});
        transactionManager.commit(stat);
        log.info(row + " package deleted");
        return row;
    }

    @Override
    public int changeItemsInPackage(String packageName,String content) {
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);

        String sql = "UPDATE package SET content=?  WHERE name = ? ";
        int row = jdbcTemplate.update(sql, new Object[]{content,packageName});
        transactionManager.commit(stat);
        log.info(row);
        return row;
    }

    @Override
    public int changePrice(String packageName,double price) {
        TransactionDefinition tr_def = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(tr_def);

        String sql = "UPDATE package SET price=?  WHERE name = ? ";
        int row = jdbcTemplate.update(sql, new Object[]{price,packageName});
        transactionManager.commit(stat);
        log.info(row);
        return row;    }


}
