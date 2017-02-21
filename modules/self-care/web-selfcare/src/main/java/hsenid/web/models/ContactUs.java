package hsenid.web.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class ContactUs {


    @Pattern(regexp = "^(?!\\s*$|\\s).*$", message = "{error.contactus.inquiryType}")
    private String inquiryType;


    @Pattern(regexp = "^(?!\\s*$|\\s).*$", message = "{error.contactus.title}")
    private String title;

    @Pattern(regexp = "^(?!\\s*$|\\s).*$", message = "{error.contactus.name}")
    private String name;


    @Email(message = "{error.contactus.contactEmail}")
    @NotEmpty(message = "{error.contactus.contactEmail}")
    private String contactEmail;

    @Pattern(regexp = "^0[0-9]{9}$", message = "{error.contactus.contactNo}")
    private String contactNo;


    @Pattern(regexp = "^(?!\\s*$|\\s).*$", message = "{error.contactus.msg}")
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
