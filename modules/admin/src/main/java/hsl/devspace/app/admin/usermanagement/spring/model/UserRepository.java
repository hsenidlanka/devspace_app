package hsl.devspace.app.admin.usermanagement.spring.model;

/**
 * Created by hsenid on 7/15/16.
 */
public class UserRepository {

    private int id;
    private String name;
    private String email;
    private String address;
    private String telephone;

    public UserRepository() {
    }

    public UserRepository(String name, String email, String address, String telephone) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.telephone = telephone;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone(){
        return telephone;
    }

    public void setTelephone(String telephone){
        this.telephone=telephone;
    }

}
