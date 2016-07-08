package hsl.devspace.app.corelogic.domain;

import java.util.Date;

/**
 * Created by hsenid on 7/4/16.
 */
public class User {
    private int usr_id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean isConfirmed;
    private Date dob;
    private String addressL1;
    private String addressL2;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getUsr_id() {
        return usr_id;
    }

    public void setUsr_id(int usr_id) {
        this.usr_id = usr_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddressL1() {
        return addressL1;
    }

    public void setAddressL1(String addressL1) {
        this.addressL1 = addressL1;
    }

    public String getAddressL2() {
        return addressL2;
    }

    public void setAddressL2(String addressL2) {
        this.addressL2 = addressL2;
    }

    public User( String username, String password) {

        this.username = username;
        this.password = password;
    }

    public User() {
      /* User user=new User();
        user.getUsername();*/
    }

    public User(String addressL2, String addressL1, Date dob,
                String password, String username, String lastName, String firstName) {
        this.addressL2 = addressL2;
        this.addressL1 = addressL1;
        this.dob = dob;
        this.password = password;
        this.username = username;
        this.lastName = lastName;
        this.firstName = firstName;
    }
}
