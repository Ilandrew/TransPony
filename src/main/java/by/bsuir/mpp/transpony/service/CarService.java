package by.bsuir.mpp.transpony.service;

import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.dao.CarDao;
import by.bsuir.mpp.transpony.dao.factory.DaoFactory;
import by.bsuir.mpp.transpony.entity.Car;

import java.util.List;

public class CarService {

    private static final CarDao dao = DaoFactory.getDaoFactory().getCarDao();

    public static List<Car> getFreeCar() throws ServiceException {
        try {
            return dao.getFreeCars();
        } catch (DaoException e) {
            throw new ServiceException("can't get free car.", e);
        }
    }

    public static void addNewCar(Car car) throws ServiceException {
        try {
            dao.add(car);
        } catch (DaoException e) {
            throw new ServiceException("can't add new car.", e);
        }
    }

    public static void deleteCar(Car car) throws ServiceException {
        try {
            dao.delete(car);
        } catch (DaoException e) {
            throw new ServiceException("can't remove this car.", e);
        }
    }

    public static void updateCar(Car car) throws ServiceException {
        try {
            dao.update(car);
        } catch (DaoException e) {
            throw new ServiceException("can't update this car.", e);
        }
    }

    public static Car getById(Integer id) throws ServiceException {
        try {
            return dao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException("can't get for id car.", e);
        }
    }
}
