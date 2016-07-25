package hsl.devspace.app.admin.usermanagement.spring.repository;

import hsl.devspace.app.admin.usermanagement.spring.model.User;

import java.util.List;

/**
 * Data Access Object interface file
 * this interface defines methods for performing CRUD operations on the users table
 */
public interface UserRepository {

//    public  void setDataSource(DataSource ds);

    public void saveOrUpdate(User user);

    public void delete(String username);

    public User get(String username);

    public List<User> list();


}
