package hsenidlanka.devspace.app.corelogic.repository;

/**
 * Created by hsenid on 7/4/16.
 */
public interface UserRepository {

    void addUser();
    void deleteUser(int usr_id);
    String changePassword(String password);
}
