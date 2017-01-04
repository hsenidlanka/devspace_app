package hsenid.web.models;

import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class EmailVerificationModel {

    @NotEmpty
    private String username;

    @Size(min = 32, message = "Please Enter a valid Verification code")
    private String verificationCode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
