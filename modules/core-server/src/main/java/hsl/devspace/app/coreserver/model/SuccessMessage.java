package hsl.devspace.app.coreserver.model;

import com.owlike.genson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Kasun Dinesh on 7/8/16.
 * This is the model/template of the success message to pass as a response.
 */
@XmlRootElement
public class SuccessMessage {
    private String status;
    private int code;
    private String message;
    private List<Data> dataList=new ArrayList<Data>();
    private List<Link> links=new ArrayList<Link>();

    public SuccessMessage() {
    }

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

    @JsonProperty("data")
    public List<Data> getDataList() {
        return dataList;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }

    @JsonProperty("links")
    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    // Add HATEOAS links
    public void addLink(String url, String rel){
        Link link=new Link();
        link.setLink(url);
        link.setRel(rel);
        links.add(link);
    }

    // Add data fields to response
    public void addData(String key, Object value){
        Data data=new Data();
        data.setKey(key);
        data.setValue(value);
        dataList.add(data);
    }
}
