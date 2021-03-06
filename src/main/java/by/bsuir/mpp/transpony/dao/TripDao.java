package by.bsuir.mpp.transpony.dao;

import by.bsuir.mpp.transpony.entity.Trip;

import java.util.List;

public interface TripDao {
    List<Trip> getAll() throws DaoException;
    Trip getById(Integer index) throws DaoException;
    void updateStatus(Trip trip, String statusName) throws DaoException;
    List<Trip> getByDriverId(Integer driverId) throws DaoException;
    List<Trip> getWithoutFuel() throws DaoException;
    void update(Trip trip) throws DaoException;
    void delete(Trip trip) throws DaoException;
    void add(Trip trip) throws DaoException;
    List<String> getAllTripsStatuses() throws DaoException;
}
