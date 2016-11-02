package hsenid.web.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ContactUs {


    @NotEmpty
    private String inquiryType;


    @NotEmpty
    private String title;

    @Length(min = 2, message = "Please Enter a valid name")
    private String name;


    @Email(message = "Please enter an valid email!")
    @NotEmpty(message = "Please enter an valid email!")
    private String contactEmail;

    @Pattern(regexp = "^0[0-9]{9}$", message = "Please Enter Valid Contact Number!")
    private String contactNo;


    @Length(min = 3, message = "Please Enter your message!")
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

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
