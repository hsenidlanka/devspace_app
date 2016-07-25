package hsl.devspace.app.admin.usermanagement.spring.model;

/**
 * domain class
 */
public class User {

    private int id;
    private String username;
    private String name;
    private String email;
    private String address;
    private String telephone;

    public User() {
    }

    public User(String username, String name, String email, String address, String telephone) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.telephone = telephone;
        this.username = username;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }

    public String getUsename(){
        return username;
    }
    public void setUsername(String username){
        this.username=username;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone(){
        return telephone;
    }

    public void setTelephone(String telephone){
        this.telephone=telephone;
    }

}
