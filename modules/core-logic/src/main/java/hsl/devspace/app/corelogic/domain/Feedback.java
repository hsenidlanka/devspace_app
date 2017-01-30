package hsl.devspace.app.corelogic.domain;

import java.sql.Time;
import java.util.Date;

/**
 * Created by hsenid on 9/19/16.
 */
public class Feedback {
    private Date date;
    private Time time;
    private String comment;
    private int numberOfStars;
    private String customerUserName;
    private String itemName;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(int numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public String getCustomerUserName() {
        return customerUserName;
    }

    public void setCustomerUserName(String customerUserName) {
        this.customerUserName = customerUserName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Feedback(Date date, Time time, String comment, int numberOfStars, String customerUserName, String itemName) {
        this.date = date;
        this.time = time;
        this.comment = comment;
        this.numberOfStars = numberOfStars;
        this.customerUserName = customerUserName;
        this.itemName = itemName;
    }

    public Feedback(String comment, int numberOfStars, String customerUserName, String itemName) {

        this.comment = comment;
        this.numberOfStars = numberOfStars;
        this.customerUserName = customerUserName;
        this.itemName = itemName;
    }

    public Feedback() {
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "date=" + date +
                ", time=" + time +
                ", comment='" + comment + '\'' +
                ", numberOfStars=" + numberOfStars +
                ", customerId=" + customerUserName +
                ", itemName='" + itemName + '\'' +
                '}';
    }
}

