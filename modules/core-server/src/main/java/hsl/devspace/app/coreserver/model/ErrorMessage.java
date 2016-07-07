package hsl.devspace.app.coreserver.model;

import com.owlike.genson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hsenid on 7/5/16.
 */

@XmlRootElement
public class ErrorMessage {

    private int errorCode;
    private String errorMessage;
    private String description;

    public ErrorMessage() {
    }

    public ErrorMessage(int errorCode, String errorMessage, String description) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.description=description;
    }

    @JsonProperty("error-code")
    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @JsonProperty("error-message")
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
