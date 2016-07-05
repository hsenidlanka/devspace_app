package hsl.devspace.app.corelogic.repository;

/**
 * Created by hsenid on 7/5/16.
 */
public interface UserUpdates {
    void deleteUser(String username);

    String changePassword();

    boolean confirmPassword();
}
