package hsl.devspace.app.corelogic.repository.Package;

import hsl.devspace.app.corelogic.domain.Package;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
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
    //private static org.apache.log4j.Logger log = Logger.getLogger(PackageRepositoryImpl.class);
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
                "(name,content,price,image) VALUES (?,?,?,?)";
        row = jdbcTemplate.update(sql, new Object[]{pack.getPackName(), pack.getContent(), pack.getPrice(), ims});
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
        String sql = "UPDATE package SET content=?,price=?,image=? WHERE name = ? ";
        int row = jdbcTemplate.update(sql, new Object[]{updatedPackage.getContent(),updatedPackage.getPrice(),updatedPackage.getImage(),updatedPackage.getPackName()});
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
            packages.setContent(mp.get(i).get("content").toString());
            packages.setPrice(Double.parseDouble(mp.get(i).get("price").toString()));
            packages.setImage(mp.get(i).get("image").toString());
            pack.add(packages);


        }
        log.info("{}",pack);
        return pack;
    }

}
