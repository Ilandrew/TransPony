package by.bsuir.mpp.transpony.entity;

public class DeliveryPoint {
    protected Integer id;
    protected String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean equals(DeliveryPoint deliveryPoint) {
        return deliveryPoint.getAddress().equals(getAddress()) ||
                deliveryPoint.getId().equals(getId());
    }
}
