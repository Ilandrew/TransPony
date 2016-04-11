package by.bsuir.mpp.transpony.dao;

import by.bsuir.mpp.transpony.entity.Trip;

import java.util.List;

public interface ITrip {

    List<Trip> getAll() throws DaoException;
    Trip getForIndex(Integer index) throws DaoException;
    void changeStatus(Trip trip, String statusName) throws DaoException;

    void update(Trip trip) throws DaoException;
    void delete(Trip trip) throws DaoException;
    void add(Trip trip) throws DaoException;

    List<String> getAllStatus() throws DaoException;


}
