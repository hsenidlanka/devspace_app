package hsenid.web.models;

import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ChangePassword {

    @Size(min = 6, message = "{error.changepassword.currentPassword}")
    private String currentPassword;

    @Size(min = 6, message = "{error.changepassword.newPassword}")
    private String newPassword;

    @Size(min = 6, message = "{error.changepassword.confirmNewPassword}")
    private String confirmNewPassword;

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }
}
