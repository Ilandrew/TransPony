package by.bsuir.mpp.transpony.dao;


import by.bsuir.mpp.transpony.entity.CheckPoint;
import by.bsuir.mpp.transpony.entity.Type;

import java.util.List;

public interface CheckPointDao {
    List<CheckPoint> getAll() throws DaoException;
    List<CheckPoint> getByRouteId(Integer routeID) throws DaoException;
    void updateRouteCheckpoints(List<CheckPoint> checkPoints, Integer idRoute) throws DaoException;
    Integer add(CheckPoint checkPoint) throws DaoException;
    void delete(CheckPoint checkPoint) throws DaoException;
    void update(CheckPoint checkPoint) throws DaoException;
    CheckPoint getById(Integer index) throws DaoException;
    List<Type> getAllTypes() throws DaoException;
}
