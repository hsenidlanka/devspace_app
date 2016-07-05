package hsenidlanka.devspace.app.corelogic.repository;

import hsenidlanka.devspace.app.corelogic.domain.User;

/**
 * Created by hsenid on 7/4/16.
 */
public interface UserRepository {


    public void addUser(User user);

    int deleteUser();

    String changePassword();

    boolean confirmPassword();
}
