package hsenid.web.models;

import javax.validation.constraints.Pattern;

public class RegisterModal extends User {

    @Pattern(regexp = "^(?!\\s*$|\\s).*$", message = "{error.changepassword.currentPassword}")
    private String password;
    @Pattern(regexp = "^(?!\\s*$|\\s).*$", message = "{error.changepassword.currentPassword}")
    private String confirmPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
