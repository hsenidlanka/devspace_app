package hsl.devspace.app.corelogic.repository.Package;

import hsl.devspace.app.corelogic.domain.Package;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hsenid on 9/20/16.
 */
public class PackageRepositoryImpl implements PackageRepository {
    //Package pkg=new Package();
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;
    org.slf4j.Logger log = LoggerFactory.getLogger(PackageRepositoryImpl.class);


    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);

    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;

    }

    /*Add new package*/
    @Override
    public int add(Package pack) {
        int row;
       /* MultipartFile img = pack.getImageUrl();
        String ims = img.getOriginalFilename();*/
        String sql = "INSERT INTO package " +
                "(name,price,image) VALUES (?,?,?)";
        row = jdbcTemplate.update(sql, new Object[]{pack.getPackName(), pack.getPrice(), pack.getImage()});
        log.debug("{} new package added", row);
        return row;
    }

    /*delete specific package*/
    @Override
    public int delete(String packageName) {

        String sql = "DELETE FROM package WHERE name = ?";
        int row = jdbcTemplate.update(sql, new Object[]{packageName});
        log.debug("{} package deleted", row);
        return row;
    }

    /*update details of package*/
    @Override
    public int update(Package updatedPackage) {

       /* MultipartFile img = updatedPackage.getImageUrl();
        String ims = img.getOriginalFilename();*/
        String sql = "UPDATE package SET price=?,image=? WHERE name = ? ";
        int row = jdbcTemplate.update(sql, new Object[]{updatedPackage.getPrice(), updatedPackage.getImage(), updatedPackage.getPackName()});
        log.debug("{}", row);
        return row;
    }

    public int updateContent(List<Package> content) {
        int row = 0;
        for (int i = 0; i < content.size(); i++) {
            String sql = "UPDATE package_item SET item_id=(SELECT id FROM item WHERE name=?),quantity=?,size=? WHERE package_id =(SELECT id FROM package WHERE name=?) ";
            row = jdbcTemplate.update(sql, new Object[]{content.get(0).getItemName(), content.get(0).getQuantity(), content.get(0).getSize(), content.get(0).getPackName()});
        }
        log.debug("{}", row);
        return row;
    }


    /*change price of a package*/
    @Override
    public int changePrice(String packageName, double price) {

        String sql = "UPDATE package SET price=?  WHERE name = ? ";
        int row = jdbcTemplate.update(sql, new Object[]{price, packageName});
        log.debug("{}", row);
        return row;
    }

    /*view all details of package*/
    @Override
    public List<Package> selectAll() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM package");
        List<Package> pack = new ArrayList<Package>();

        for (int i = 0; i < mp.size(); i++) {
            Package packages = new Package();
            packages.setPackageId(Integer.parseInt(mp.get(i).get("id").toString()));
            packages.setPackName(mp.get(i).get("name").toString());
            packages.setPrice(Double.parseDouble(mp.get(i).get("price").toString()));
            packages.setImage(mp.get(i).get("image").toString());
            pack.add(packages);


        }
        log.debug("{}", pack);
        return pack;
    }

    @Override
    public int addContent(String packName, List<Package> content) {
        int row = 0;
        for (int i = 0; i < content.size(); i++) {
            //String packName = content.get(i).getPackName();
            String itemName = content.get(i).getItemName();
            log.error("Item name in core logic {}", itemName);
            int quantity = content.get(i).getQuantity();
            String size = content.get(i).getSize();
            String sql = "INSERT INTO package_item " +
                    "(package_id,item_id,quantity,size) VALUES ((SELECT id FROM package WHERE name=?),(SELECT id FROM item WHERE name=?),?,?)";
            row = jdbcTemplate.update(sql, new Object[]{packName, itemName, quantity, size});
        }
        log.debug("{} new content added", row);
        return row;
    }

    /**
     * if unique -> true
     */
    @Override
    public boolean checkUniquePackage(String packName) {
        boolean result = false;
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM package WHERE name=?", packName);
        if (mp.size() == 0) {
            result = true;
        }
        log.debug("{}", result);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int addPackage(Package pack, List<Package> content) {
        int j = 0;
        TransactionDefinition trDef = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(trDef);
        try {
            add(pack);
            addContent(pack.getPackName(), content);
            transactionManager.commit(stat);
            j = 1;
        } catch (Exception e) {
            log.debug(e.getMessage());
            transactionManager.rollback(stat);
        }
        return j;
    }

    @Override
    public int deleteContent(String packageName) {
        String sql = "DELETE FROM package_item WHERE package_id = (SELECT id FROM package WHERE name=?)";
        int row = jdbcTemplate.update(sql, new Object[]{packageName});
        log.debug("{} package deleted", row);
        return row;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int deletePackage(String packageName) {
        int j = 0;
        TransactionDefinition trDef = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(trDef);
        try {
            delete(packageName);
            deleteContent(packageName);
            transactionManager.commit(stat);
            j = 1;
        } catch (Exception e) {
            log.debug(e.getMessage());
            transactionManager.rollback(stat);
        }
        return j;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int updatePackage(Package updatedPackage, List<Package> content) {
        int j = 0;
        TransactionDefinition trDef = new DefaultTransactionDefinition();
        TransactionStatus stat = transactionManager.getTransaction(trDef);
        try {
            update(updatedPackage);
            updateContent(content);
            transactionManager.commit(stat);
            j = 1;
        } catch (Exception e) {
            log.debug(e.getMessage());
            transactionManager.rollback(stat);
        }
        return j;
    }

    /*retrieve content details of a given package*/
    @Override
    public List<ReturnTypeResolver> getContentDetails(String packageName) {

        List<ReturnTypeResolver> items = new ArrayList<ReturnTypeResolver>();

        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT id FROM package WHERE name=?", packageName);
        if (mp.size() > 0) {
            List<Map<String, Object>> mp2 = jdbcTemplate.queryForList("SELECT item_id FROM package_item WHERE package_id=?", mp.get(0).get("id"));

            for (int i = 0; i < mp2.size(); i++) {
                ReturnTypeResolver rv = new ReturnTypeResolver();
                List<String> sizes = new ArrayList<String>();

                List<Map<String, Object>> mp1 = jdbcTemplate.queryForList("SELECT name FROM item WHERE id=?", mp2.get(i).get("item_id"));
                List<Map<String, Object>> mp3 = jdbcTemplate.queryForList("SELECT size FROM size WHERE item_id=?", mp2.get(i).get("item_id"));
                List<Map<String, Object>> mp4 = jdbcTemplate.queryForList("SELECT c.name FROM category c WHERE id=(SELECT category_id FROM sub_category WHERE id=(SELECT sub_category_id FROM item WHERE id=?))", mp2.get(i).get("item_id"));

                rv.setItem(mp1.get(0).get("name").toString());
                for (int j = 0; j < mp3.size(); j++) {
                    sizes.add(mp3.get(j).get("size").toString());
                }
                rv.setSize(sizes);
                rv.setCategoryName(mp4.get(0).get("name").toString());

                items.add(rv);

            }
        }
        log.info("pack {}", items);
        return items;

    }


}
