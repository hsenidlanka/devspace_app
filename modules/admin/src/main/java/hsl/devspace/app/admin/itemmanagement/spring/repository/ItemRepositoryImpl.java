package hsl.devspace.app.admin.itemmanagement.spring.repository;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import hsl.devspace.app.admin.itemmanagement.spring.model.Item;
import org.springframework.jdbc.core.JdbcTemplate;

public class ItemRepositoryImpl implements ItemRepository {

    //declare jdbc template
    private ComboPooledDataSource dataSource;
    JdbcTemplate jdbcTemplate;

    public void setDataSource(ComboPooledDataSource dataSource){
        this.dataSource =dataSource;
        this.jdbcTemplate = new JdbcTemplate();
    }

    @Override
    public void addItem(Item item){

    }
}
