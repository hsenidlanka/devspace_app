package hsl.devspace.app.corelogic.repository;

import hsl.devspace.app.corelogic.domain.User;

/**
 * Created by hsenid on 7/4/16.
 */
public interface UserRepository {


    public void addUser(User user);
    void deleteUser(String username);

    void changePassword(String username, String password);

    boolean confirmPassword();


}
