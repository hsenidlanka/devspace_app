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
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hsenid on 9/20/16.
 */
public class PackageRepositoryImpl implements PackageRepository  {
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
        MultipartFile img = pack.getImageUrl();
        String ims = img.getOriginalFilename();
        String sql = "INSERT INTO package " +
                "(name,price,image) VALUES (?,?,?)";
        row = jdbcTemplate.update(sql, new Object[]{pack.getPackName(), pack.getPrice(), ims});
        log.info("{} new package added",row);
        return row;
    }

    /*delete specific package*/
    @Override
    public int delete(String packageName) {

        String sql = "DELETE FROM package WHERE name = ?";
        int row = jdbcTemplate.update(sql, new Object[]{packageName});
        log.info("{} package deleted",row);
        return row;
    }

    /*update details of package*/
    @Override
    public int updatePackage(Package updatedPackage) {
        String sql = "UPDATE package SET price=?,image=? WHERE name = ? ";
        int row = jdbcTemplate.update(sql, new Object[]{updatedPackage.getPrice(), updatedPackage.getImage(), updatedPackage.getPackName()});
        log.info("{}",row);
        return row;
    }

    /*change price of a package*/
    @Override
    public int changePrice(String packageName,double price) {

        String sql = "UPDATE package SET price=?  WHERE name = ? ";
        int row = jdbcTemplate.update(sql, new Object[]{price, packageName});
        log.info("{}",row);
        return row;
    }

    /*view all details of package*/
    @Override
    public List<Package> selectAll() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM package");
        List<Package> pack=new ArrayList<Package>();

        for (int i=0;i<mp.size();i++){
            Package packages = new Package();
            packages.setPackageId(Integer.parseInt(mp.get(i).get("id").toString()));
            packages.setPackName(mp.get(i).get("name").toString());
            packages.setPrice(Double.parseDouble(mp.get(i).get("price").toString()));
            packages.setImage(mp.get(i).get("image").toString());
            pack.add(packages);


        }
        log.info("{}",pack);
        return pack;
    }

    @Override
    public int addContent(List<Package> content) {
        int row = 0;
        for (int i = 0; i < content.size(); i++) {
            String packName = content.get(i).getPackName();
            String itemName = content.get(i).getItemName();
            int quantity = content.get(i).getQuantity();
            String size = content.get(i).getSize();
            String sql = "INSERT INTO package_item " +
                    "(package_id,item_id,quantity,size) VALUES (SELECT id FROM package WHERE name=?,SELECT id FROM item WHERE name=?,?,?)";
            row = jdbcTemplate.update(sql, new Object[]{packName, itemName, quantity, size});
        }
        log.info("{} new content added", row);
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
        log.info("{}", result);
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
            addContent(content);
            transactionManager.commit(stat);
            j = 1;
        } catch (Exception e) {
            log.info(e.getMessage());
            transactionManager.rollback(stat);
        }
        return j;
    }

}
