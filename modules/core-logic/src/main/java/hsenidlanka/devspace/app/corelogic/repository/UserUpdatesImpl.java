package hsenidlanka.devspace.app.corelogic.repository;

import hsenidlanka.devspace.app.corelogic.domain.User;

/**
 * Created by hsenid on 7/5/16.
 */
public class UserUpdatesImpl implements UserUpdates {
    User user=new User();
    @Override
    public int deleteUser() { return user.getUsr_id(); }

    @Override
    public String changePassword() {
        return user.getPassword();
    }

    @Override
    public boolean confirmPassword() {
        return user.isConfirmed();
    }
}
