package hsenid.web.models;

import org.json.simple.JSONObject;

import java.util.List;

/**
 * Created by hsenid on 10/26/16.
 */
public class ServerResponseMessage {
    private String status;
    private int code;
    private String message;
    private List<JSONObject> data;
    private List<Link> links;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<JSONObject> getData() {
        return data;
    }

    public void setData(List<JSONObject> data) {
        this.data = data;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
