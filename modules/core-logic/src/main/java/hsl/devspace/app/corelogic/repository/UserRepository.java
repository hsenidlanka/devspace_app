package hsl.devspace.app.corelogic.repository;

import hsl.devspace.app.corelogic.domain.User;
import org.springframework.dao.DuplicateKeyException;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Created by hsenid on 7/4/16.
 */
public interface UserRepository {


    public int addUser(User user)throws SQLIntegrityConstraintViolationException,DuplicateKeyException;
    int deleteUser(String username)throws SQLException;

    void changePassword(String username, String password)throws SQLException;

    boolean confirmPassword()throws SQLException;
    boolean loginAuthenticate(User user)throws SQLException;


}
