package hsenid.web.bindclasses;

public class BooleanResponse {
    boolean userAvailable;

    public BooleanResponse(boolean userAvailable) {
        this.userAvailable = userAvailable;
    }

    public boolean isUserAvailable() {
        return userAvailable;
    }

    public void setUserAvailable(boolean userAvailable) {
        this.userAvailable = userAvailable;
    }
}
