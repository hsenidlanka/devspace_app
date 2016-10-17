package hsenid.web.bindclasses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ReplyFromServer {
    private String status;
    private int code;
    private String message;
    private List<Link> links;
    private List<UserData> data;

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @JsonProperty("code")
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    @JsonProperty("links")
    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @JsonProperty("data")
    public List<UserData> getData() {
        return data;
    }

    public void setData(List<UserData> data) {
        this.data = data;
    }









/*

    {
        "status": "success",
            "code": 200,
            "message": "customer data retrieved",
            "data": [
        {
            "firstName": "Capricone",
                "lastName": "Silver",
                "registeredDate": "2016-09-13",
                "addressLine03": "Axa town",
                "mobile": "0859632586",
                "addressLine02": "Town hall",
                "addressLine01": "Creeps Lane",
                "title": "Ms",
                "email": "hulkmail!@gmail.com",
                "username": "testre"
        }
        ],
        "links": [
        {
            "rel": "self",
                "link": "http://localhost:2222/pizza-shefu/api/v1.0/customers/testre/"
        }
        ]
    }*/
}
