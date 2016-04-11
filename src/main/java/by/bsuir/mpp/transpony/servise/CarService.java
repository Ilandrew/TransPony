package by.bsuir.mpp.transpony.servise;

import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.dao.ICar;
import by.bsuir.mpp.transpony.dao.factory.DaoFactory;
import by.bsuir.mpp.transpony.entity.Car;

import java.util.List;

public class CarService {

    private static final ICar dao = DaoFactory.getDaoFactory().getCarDao();

    public static List<Car> getFreeCar() throws ServiceException {
        try {
            return dao.getFreeCars();
        } catch (DaoException e) {
            throw new ServiceException("can't get free car.", e);
        }
    }

    public static void addNewCar(Car car) throws ServiceException {
        try {
            dao.addNewCar(car);
        } catch (DaoException e) {
            throw new ServiceException("can't add new car.", e);
        }
    }

    public static void deleteCar(Car car) throws ServiceException {
        try {
            dao.deleteCar(car);
        } catch (DaoException e) {
            throw new ServiceException("can't remove this car.", e);
        }
    }

    public static void updateCar(Car car) throws ServiceException {
        try {
            dao.updateCar(car);
        } catch (DaoException e) {
            throw new ServiceException("can't update this car.", e);
        }
    }

    public static Car getForIndex(Integer index) throws ServiceException {
        try {
            return dao.getForIndex(index);
        } catch (DaoException e) {
            throw new ServiceException("can't get for index car.", e);
        }
    }
}
