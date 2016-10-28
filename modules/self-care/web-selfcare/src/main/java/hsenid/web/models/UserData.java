package hsenid.web.models;

/**
 * Created by hsenid on 10/17/16.
 */
public class UserData extends UpdateUser{

    String registeredDate;

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

}
