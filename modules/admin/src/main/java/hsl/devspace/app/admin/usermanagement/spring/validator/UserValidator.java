
package hsl.devspace.app.admin.usermanagement.spring.validator;



import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserValidator {

    //    @Pattern(regexp = "/^[a-zA-Z]{1,}$/", message = "Please enter a ti   jjtle!")
    @NotEmpty(message = "Please select a title")
    @Size(min = 1,  message = "Please select a title")
    private String title;

    @Pattern(regexp = "^(?!\\s*$|\\s).*$",  message = "Please enter your first name!")
    private String firstName;

    @Pattern(regexp = "^(?!\\s*$|\\s).*$", message = "Please enter your last name!")
    private String lastName;

    @NotEmpty(message = "Please enter your email!")
    @Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$" , message = "Please enter a valid email!")
    private String email;


    @Pattern(regexp = "^(?!\\s*$|\\s).*$", message = "Please enter a valid username!")
    private String username;

    @Pattern(regexp = "^0[0-9]{9}$", message = "Please Enter Valid Contact Number!")
    private String mobile;

    @Pattern(regexp = "^(?!\\s*$|\\s).*$", message = "Please enter address line one")
    private String addressL1;

    @Pattern(regexp = "^(?!\\s*$|\\s).*$", message = "Please enter address line two")
    private String addressL2;

    private String addressL3;


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

    public String  getAddressL3() {
        return addressL3;
    }

    public void setAddressLine03(String addressL3) {
        this.addressL3 = addressL3;
    }
}