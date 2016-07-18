package hsl.devspace.app.coreserver.model;

import com.owlike.genson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

/**
 * Created by hsenid on 7/8/16.
 * This is the model/template of the success message to pass as a response.
 */
@XmlRootElement
public class SuccessModel {
    private String status;
    private int code;
    private String message;
    private Map<String, Object> data;

    public SuccessModel() {
    }

    public SuccessModel(String status, int code, String message, Map<String, Object> data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
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
    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
