package by.bsuir.mpp.transpony.dao;


import by.bsuir.mpp.transpony.entity.CheckPoint;

import java.util.List;

public interface ICheckPoint {

    List<CheckPoint> getAll() throws DaoException;
    List<CheckPoint> getForRoute() throws DaoException;

    void updateForRow(List<CheckPoint> checkPoints, Integer idRoute) throws DaoException;

    void addNew(CheckPoint checkPoint) throws DaoException;
    void delete(CheckPoint checkPoint) throws DaoException;
    void update(CheckPoint checkPoint) throws DaoException;



}
