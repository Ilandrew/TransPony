package by.bsuir.mpp.transpony.service;


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

     public static List<Trip> getByDriverId(Integer driverId) throws ServiceException {
         try {
             return dao.getByDriverId(driverId);
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

    public static Trip getById(Integer index) throws ServiceException {
        try {
            return dao.getById(index);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }
    public static void updateStatus(Trip trip, String statusName) throws ServiceException {
        try {
            dao.updateStatus(trip, statusName);
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

    public static List<String> getAllTripsStatuses() throws ServiceException {
        try {
            return dao.getAllTripsStatuses();
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }
}
