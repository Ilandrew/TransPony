package by.bsuir.mpp.transpony.entity;

import java.util.List;

public class Receiver {
    protected Integer id;
    protected String name;
    protected String address;
    protected String phone;
    protected String email;
    protected List<DeliveryPoint> deliveryPoints;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<DeliveryPoint> getDeliveryPoints() {
        return deliveryPoints;
    }

    public void setDeliveryPoints(List<DeliveryPoint> deliveryPoints) {
        this.deliveryPoints = deliveryPoints;
    }
}
