package hsl.devspace.app.coreserver.model;

/**
 * Created by Kasun Dinesh on 7/22/16.
 * This is the model/template of the data to pass as a response.
 */
public class Data {
    private String key;
    private Object value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
