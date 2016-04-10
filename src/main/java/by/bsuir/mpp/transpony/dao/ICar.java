package by.bsuir.mpp.transpony.dao;

import by.bsuir.mpp.transpony.entity.Car;

import java.util.List;

public interface ICar {

    List<Car> getFreeCars() throws DaoException;
    void addNewCar(Car car) throws DaoException;
    void deleteCar(Car car) throws DaoException;
    void updateCar(Car car) throws DaoException;

    Car getForIndex(Integer cadId) throws DaoException;
}
