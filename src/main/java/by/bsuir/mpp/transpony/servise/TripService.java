package by.bsuir.mpp.transpony.servise;


import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.dao.TripDao;
import by.bsuir.mpp.transpony.dao.factory.DaoFactory;
import by.bsuir.mpp.transpony.entity.Trip;

import java.util.List;

public class TripService {
    private static final TripDao dao = DaoFactory.getDaoFactory().getTripDao();

    public static List<Trip> getAll() throws ServiceException {
        try {
            return dao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }

     public static List<Trip> getForDriver(Integer driverId) throws ServiceException {
         try {
             return dao.getForDriver(driverId);
         } catch (DaoException e) {
             throw new ServiceException("", e);
         }
     }

    public static List<Trip> getWithoutFuel() throws ServiceException {
        try {
            return dao.getWithoutFuel();
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }

    public static Trip getForIndex(Integer index) throws ServiceException {
        try {
            return dao.getForIndex(index);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }
    public static void changeStatus(Trip trip, String statusName) throws ServiceException {
        try {
            dao.changeStatus(trip, statusName);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }

    public static void update(Trip trip) throws ServiceException {
        try {
            dao.update(trip);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }
    public static void delete(Trip trip) throws ServiceException {
        try {
            dao.delete(trip);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }
    public static void add(Trip trip) throws ServiceException {
        try {
            dao.add(trip);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }

    public static List<String> getAllStatus() throws ServiceException {
        try {
            return dao.getAllStatus();
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }
}
