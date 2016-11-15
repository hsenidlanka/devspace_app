package hsenid.web.models;

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

}
