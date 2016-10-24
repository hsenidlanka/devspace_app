package hsl.devspace.app.corelogic.domain;

/**
 * Created by hsenid on 10/20/16.
 */
public class ContactUs {
    private int id;
    private int inquiryType;
    private String title;
    private String name;
    private String email;
    private String mobile;
    private String message;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInquiryType() {
        return inquiryType;
    }

    public void setInquiryType(int inquiryType) {
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ContactUs(int inquiryType, String title, String name, String email, String mobile, String message) {
        this.inquiryType = inquiryType;
        this.title = title;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.message = message;
    }

    public ContactUs() {
    }

    public ContactUs(int inquiryType, String title, String name, String email, String mobile, String message, String type) {
        this.inquiryType = inquiryType;
        this.title = title;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.message = message;
        this.type = type;
    }
}
