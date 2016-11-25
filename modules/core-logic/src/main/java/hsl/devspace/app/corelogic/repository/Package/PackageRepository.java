package hsl.devspace.app.corelogic.repository.Package;

import hsl.devspace.app.corelogic.domain.Package;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * Created by hsenid on 9/20/16.
 */
public interface PackageRepository {
    int add(hsl.devspace.app.corelogic.domain.Package pack) throws SQLIntegrityConstraintViolationException;

    int delete(String packageName);

    int update(hsl.devspace.app.corelogic.domain.Package updatedPackage);

    int changePrice(String packageName, double price);

    List<hsl.devspace.app.corelogic.domain.Package> selectAll();

    int addContent(String packName, List<hsl.devspace.app.corelogic.domain.Package> content);

    boolean checkUniquePackage(String packName);

    int addPackage(hsl.devspace.app.corelogic.domain.Package pack, List<Package> content);

    int deleteContent(String packageName);

    int deletePackage(String packageName);

    int updatePackage(Package updatedPackage, List<Package> content);

    List<ReturnTypeResolver> getContentDetails(String packageName);

    int updateContent(List<Package> content);

    int count();

    List<Package> selectAllByNamePattern(String packName);
}
