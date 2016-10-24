package hsenid.web.models;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ContactUs {

    @NotNull
    private String inquiryType;

    @NotNull
    private String title;

    @NotNull
    @Size(min = 2)
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 9, max = 10)
    private String contactNo;

    @NotNull
    @Size(min = 3)
    private String msg;

    public String getInquiryType() {
        return inquiryType;
    }

    public void setInquiryType(String inquiryType) {
        this.inquiryType = inquiryType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
