package hsenid.web.models;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

public class UpdateUser {

    @NotNull
    String title;

    @NotNull
    String firstName;

    @NotNull
    String lastName;

    @NotNull
    @Email
    String email;

    @NotNull
    String username;

    @NotNull
    String mobile;

    @NotNull
    String addressLine01;

    @NotNull
    String addressLine02;

    @NotNull
    String addressLine03;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddressLine01() {
        return addressLine01;
    }

    public void setAddressLine01(String addressLine01) {
        this.addressLine01 = addressLine01;
    }

    public String getAddressLine02() {
        return addressLine02;
    }

    public void setAddressLine02(String addressLine02) {
        this.addressLine02 = addressLine02;
    }

    public String getAddressLine03() {
        return addressLine03;
    }

    public void setAddressLine03(String addressLine03) {
        this.addressLine03 = addressLine03;
    }
}
