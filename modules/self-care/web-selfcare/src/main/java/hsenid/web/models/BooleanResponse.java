package hsenid.web.models;

public class BooleanResponse {

    private boolean userAvailable;

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
