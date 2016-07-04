package hsenidlanka.devspace.app.corelogic.repository;

import hsenidlanka.devspace.app.corelogic.domain.User;

/**
 * Created by hsenid on 7/4/16.
 */
public class UserRepositoryImpl implements UserRepository{
    User user=new User();


    @Override
    public String addUser() {
        return user.getUsername();

    }

    @Override
    public int deleteUser() {
        return user.getUsr_id();

    }

    @Override
    public String changePassword() {
        return user.getPassword();
    }
}
