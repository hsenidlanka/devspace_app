package hsl.devspace.app.corelogic.repository;

/**
 * Created by hsenid on 7/5/16.
 */
public interface UserUpdates {
    int deleteUser();

    String changePassword();

    boolean confirmPassword();
}
