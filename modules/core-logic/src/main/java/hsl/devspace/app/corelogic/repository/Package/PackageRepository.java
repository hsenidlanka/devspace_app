package hsl.devspace.app.corelogic.repository.Package;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Created by hsenid on 9/20/16.
 */
public interface PackageRepository {
    int add(hsl.devspace.app.corelogic.domain.Package pack) throws SQLIntegrityConstraintViolationException;
    int delete(String packageName);
    int changeItemsInPackage(String packageName,String content);
    int changePrice(String packageName,double price);
}
