package by.bsuir.mpp.transpony.dao;

import by.bsuir.mpp.transpony.entity.CheckPoint;
import by.bsuir.mpp.transpony.entity.Route;

import java.util.List;

public interface IRoute {

    List<Route> getAll() throws DaoException;

    void update(Route route) throws DaoException;
    void add(Route route) throws DaoException;
    void delete(Route route) throws DaoException;
    Route getForIndex(Integer index) throws DaoException;

    List<CheckPoint> getCheckPointForRoute(Integer index) throws DaoException;

}
