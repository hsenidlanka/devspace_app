package hsl.devspace.app.corelogic.domain;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by hsenid on 9/19/16.
 */
public class Delivery {
    private String agentName;
    private String recepientName;
    private String recepientAddress;
    private LocalDate deliveryDate;
    private LocalTime deliveryTime;
    private String deliveryStatus;
    private String description;
    private String deliveryMethod;

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getRecepientName() {
        return recepientName;
    }

    public void setRecepientName(String recepientName) {
        this.recepientName = recepientName;
    }

    public String getRecepientAddress() {
        return recepientAddress;
    }

    public void setRecepientAddress(String recepientAddress) {
        this.recepientAddress = recepientAddress;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public LocalTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public Delivery(String agentName, String recepientName, String recepientAddress, LocalDate deliveryDate,
                    LocalTime deliveryTime, String deliveryStatus, String description, String deliveryMethod) {
        this.agentName = agentName;
        this.recepientName = recepientName;
        this.recepientAddress = recepientAddress;
        this.deliveryDate = deliveryDate;
        this.deliveryTime = deliveryTime;
        this.deliveryStatus = deliveryStatus;
        this.description = description;
        this.deliveryMethod = deliveryMethod;
    }

    public Delivery() {
    }

    public Delivery(String recepientName, String recepientAddress, LocalDate deliveryDate, LocalTime deliveryTime, String description, String deliveryMethod) {
        this.recepientName = recepientName;
        this.recepientAddress = recepientAddress;
        this.deliveryDate = deliveryDate;
        this.deliveryTime = deliveryTime;
        this.description = description;
        this.deliveryMethod = deliveryMethod;
    }

    //For pickup
    public Delivery(LocalDate deliveryDate, LocalTime deliveryTime, String deliveryMethod) {
        this.deliveryDate = deliveryDate;
        this.deliveryTime = deliveryTime;
        this.deliveryMethod = deliveryMethod;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "agentName='" + agentName + '\'' +
                ", recepientName='" + recepientName + '\'' +
                ", recepientAddress='" + recepientAddress + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", deliveryTime=" + deliveryTime +
                ", deliveryStatus='" + deliveryStatus + '\'' +
                ", description='" + description + '\'' +
                ", deliveryMethod='" + deliveryMethod + '\'' +
                '}';
    }
}
