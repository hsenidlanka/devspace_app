package hsl.devspace.app.admin.usermanagement.spring.repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import hsl.devspace.app.admin.usermanagement.spring.model.User;
import org.apache.shiro.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * An implementation of the UserRepository interface
 */
public class UserRepositoryImpl implements UserRepository {

    //declare the  jdbcTemplate
    private ComboPooledDataSource dataSource;
    JdbcTemplate jdbcTemplate;


    public void setDataSource(ComboPooledDataSource dataSource){
        this.dataSource=dataSource;
        this.jdbcTemplate =new JdbcTemplate( dataSource);
    }


    @Override
    public void saveOrUpdate(User user) {
       //update the record
        if(user.getId() > 0){
            String updateSql= "UPDATE user SET username=? , email=?, telephone_no=?, address=? , first_name=? WHERE" +
                    "id=?";

            jdbcTemplate.update(updateSql, user.getUsename(), user.getEmail(), user.getTelephone(),user.getAddress(),
                    user.getName(), user.getId());

        }else {
            //insert a new user record
            String insertSql= "INSERT INTO user (username, email,telephone_no, address,first_name ) VALUES (?,?,?,?,?)";

            jdbcTemplate.update(insertSql, user.getUsename(), user.getEmail(), user.getTelephone(),user.getAddress(),
                    user.getName());
        }

    }

    //delete a particular user record based on a username
    @Override
    public void delete(String username) {
        String deleteSql= "DELETE FROM user WHERE username=?";
        jdbcTemplate.update(deleteSql,username);

    }

    //get a particular user details based on the username
    @Override
    public User get(String username) {
        String selectSql= "SELECT * FROM user WHERE username=" + username;

//        ResultSetExtractor is to extract a single row as a POJO.
        return jdbcTemplate.query(selectSql, new ResultSetExtractor<User>() {

            @Override
            public User extractData(ResultSet resultSet) throws SQLException, DataAccessException {

                if(resultSet.next()){
                    User user=new User();
                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setName(resultSet.getString("first_name"));
                    user.setEmail(resultSet.getString("email"));
                    user.setAddress(resultSet.getString("address"));
                    user.setTelephone(resultSet.getString("telephone_no"));
                    return  user;

                }
                return null;
            }
        });
    }

    //to get the list of users
    @Override
    public List<User> list() {

        String listSql= "SELECT * FROM user";
        List<User> listUsers= jdbcTemplate.query(listSql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                User user =new User();

                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setName(resultSet.getString("first_name"));
                user.setEmail(resultSet.getString("email"));
                user.setAddress(resultSet.getString("address"));
                user.setTelephone(resultSet.getString("telephone_no"));
                return user;
            }
        });
        return listUsers;
    }
}
