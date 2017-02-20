package hsenid.web.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class ContactUs {


    @Pattern(regexp = "^(?!\\s*$|\\s).*$", message = "Please enter inquiry type")
    private String inquiryType;


    @Pattern(regexp = "^(?!\\s*$|\\s).*$", message = "Please enter title")
    private String title;

    @Pattern(regexp = "^(?!\\s*$|\\s).*$", message = "{validation.error}")
    private String name;


    @Email(message = "Please enter an valid email!")
    @NotEmpty(message = "Please enter an valid email!")
    private String contactEmail;

    @Pattern(regexp = "^0[0-9]{9}$", message = "Please Enter Valid Contact Number!")
    private String contactNo;


    @Pattern(regexp = "^(?!\\s*$|\\s).*$", message = "Please Enter your message!")
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
