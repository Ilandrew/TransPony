package by.bsuir.mpp.transpony.entity;

import java.util.Date;

public class Trip {

    protected Integer id;
    protected String tripStatus;
    protected Date starDate;
    protected Date expectedFinishDate;
    protected Date realFinishDate;
    protected Float realFuelConsumption;
    protected Float expectedFuelConsumption;
    protected Float driverProfit;
    protected Float expenes;
    protected Waybill waybill;
    protected Route route;
    protected Car car;
    protected User driver;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(String tripStatus) {
        this.tripStatus = tripStatus;
    }

    public Date getStarDate() {
        return starDate;
    }

    public void setStarDate(Date starDate) {
        this.starDate = starDate;
    }

    public Date getExpectedFinishDate() {
        return expectedFinishDate;
    }

    public void setExpectedFinishDate(Date expectedFinishDate) {
        this.expectedFinishDate = expectedFinishDate;
    }

    public Date getRealFinishDate() {
        return realFinishDate;
    }

    public void setRealFinishDate(Date realFinishDate) {
        this.realFinishDate = realFinishDate;
    }

    public Float getRealFuelConsumption() {
        return realFuelConsumption;
    }

    public void setRealFuelConsumption(Float realFuelConsumption) {
        this.realFuelConsumption = realFuelConsumption;
    }

    public Float getExpectedFuelConsumption() {
        return expectedFuelConsumption;
    }

    public void setExpectedFuelConsumption(Float expectedFuelConsumption) {
        this.expectedFuelConsumption = expectedFuelConsumption;
    }

    public Float getDriverProfit() {
        return driverProfit;
    }

    public void setDriverProfit(Float driverProfit) {
        this.driverProfit = driverProfit;
    }

    public Float getExpenes() {
        return expenes;
    }

    public void setExpenes(Float expenes) {
        this.expenes = expenes;
    }

    public Waybill getWaybill() {
        return waybill;
    }

    public void setWaybill(Waybill waybill) {
        this.waybill = waybill;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }
}
