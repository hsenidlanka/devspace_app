package hsl.devspace.app.corelogic.repository.Package;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * Created by hsenid on 9/20/16.
 */
public interface PackageRepository {
    int add(hsl.devspace.app.corelogic.domain.Package pack) throws SQLIntegrityConstraintViolationException;
    int delete(String packageName);
    int updatePackage(hsl.devspace.app.corelogic.domain.Package updatedPackage);
    int changePrice(String packageName,double price);
    List<hsl.devspace.app.corelogic.domain.Package> selectAll();

    int addContent(hsl.devspace.app.corelogic.domain.Package content);

    boolean checkUniquePackage(String packName);
}
