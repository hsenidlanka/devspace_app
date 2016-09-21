package hsl.devspace.app.admin.itemmanagement.spring.repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import hsl.devspace.app.admin.itemmanagement.spring.model.Item;
import org.springframework.jdbc.core.JdbcTemplate;

public class ItemRepositoryImpl implements ItemRepository {

    JdbcTemplate jdbcTemplate;
    //declare jdbc template
    private ComboPooledDataSource dataSource;

    public void setDataSource(ComboPooledDataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate();
    }

    @Override
    public void addItem(Item item) {

// add a new item
        String addNewSql = "INSERT INTO item (name, description, type, image, sub_category_id) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(addNewSql, item.getName(), item.getDescript(), item.getType(), item.getImgLocation(),
                item.getSubCategory());
    }
}
