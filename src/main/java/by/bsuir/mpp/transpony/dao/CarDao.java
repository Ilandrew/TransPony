package by.bsuir.mpp.transpony.dao;

import by.bsuir.mpp.transpony.entity.Car;

import java.util.List;

public interface CarDao {
    List<Car> getFreeCars() throws DaoException;
    void add(Car car) throws DaoException;
    void delete(Car car) throws DaoException;
    void update(Car car) throws DaoException;
    Car getById(Integer carId) throws DaoException;
}
