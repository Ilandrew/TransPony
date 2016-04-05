package by.bsuir.mpp.transpony.dao;

import by.bsuir.mpp.transpony.entity.Car;

import java.util.List;

public interface ICar {

    List<Car> getFreeCars() throws DaoExeption;
    void addNewCar(Car car);
    void deleteCar(Car car);
    void updateCar(Car car);
}
