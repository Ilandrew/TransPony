package by.bsuir.mpp.transpony.entity;

import by.bsuir.mpp.transpony.entity.user.User;

import java.math.BigDecimal;
import java.util.Date;

public class Trip {
    protected Integer id;
    protected String tripStatus;
    protected Date startDate;
    protected Date expectedFinishDate;
    protected Date realFinishDate;
    protected BigDecimal realFuelConsumption;
    protected BigDecimal expectedFuelConsumption;
    protected BigDecimal driverProfit;
    protected BigDecimal expenses;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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

    public BigDecimal getRealFuelConsumption() {
        return realFuelConsumption;
    }

    public void setRealFuelConsumption(BigDecimal realFuelConsumption) {
        this.realFuelConsumption = realFuelConsumption;
    }

    public BigDecimal getExpectedFuelConsumption() {
        return expectedFuelConsumption;
    }

    public void setExpectedFuelConsumption(BigDecimal expectedFuelConsumption) {
        this.expectedFuelConsumption = expectedFuelConsumption;
    }

    public BigDecimal getDriverProfit() {
        return driverProfit;
    }

    public void setDriverProfit(BigDecimal driverProfit) {
        this.driverProfit = driverProfit;
    }

    public BigDecimal getExpenses() {
        return expenses;
    }

    public void setExpenses(BigDecimal expenses) {
        this.expenses = expenses;
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
