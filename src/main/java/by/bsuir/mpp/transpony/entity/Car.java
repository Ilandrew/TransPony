package by.bsuir.mpp.transpony.entity;

import java.math.BigDecimal;

public class Car {

    protected Integer id;
    protected String licensePlate;
    protected String vendor;
    protected String model;
    protected String type;
    protected BigDecimal fuelConsumption;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(BigDecimal fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public boolean equals (Car car) {
        if (car.getLicensePlate().equals(getLicensePlate())){
            return true;
        }
        return false;
    }
}
