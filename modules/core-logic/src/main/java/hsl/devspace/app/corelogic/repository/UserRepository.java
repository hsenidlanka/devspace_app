package hsl.devspace.app.corelogic.repository;

import hsl.devspace.app.corelogic.domain.User;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.TransientDataAccessResourceException;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

/**
 * Created by hsenid on 7/4/16.
 */
public interface UserRepository {


    public int add(User user) throws SQLIntegrityConstraintViolationException, DuplicateKeyException;

    int delete(String username) throws SQLException;

    void changePassword(String username, String password) throws SQLException;

    boolean confirmPassword() throws SQLException;

    boolean loginAuthenticate(User user) throws SQLException;
    int modify(User user)throws SQLException,TransientDataAccessResourceException;

    List<Map<String, Object>> retrieveMultipleRowsColumns(String username);
}
