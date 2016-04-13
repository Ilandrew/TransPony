package by.bsuir.mpp.transpony.dao;


import by.bsuir.mpp.transpony.entity.CheckPoint;

import java.util.List;

public interface CheckPointDao {

    List<CheckPoint> getAll() throws DaoException;
    List<CheckPoint> getForRoute(Integer routeID) throws DaoException;
    void updateForRoute(List<CheckPoint> checkPoints, Integer idRoute) throws DaoException;

    Integer addNew(CheckPoint checkPoint) throws DaoException;
    void delete(CheckPoint checkPoint) throws DaoException;
    void update(CheckPoint checkPoint) throws DaoException;
    CheckPoint getForIndex(Integer index) throws DaoException;



}
