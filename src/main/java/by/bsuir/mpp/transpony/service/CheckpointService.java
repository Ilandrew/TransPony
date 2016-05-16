package by.bsuir.mpp.transpony.service;

import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.dao.CheckPointDao;
import by.bsuir.mpp.transpony.dao.factory.DaoFactory;
import by.bsuir.mpp.transpony.entity.CheckPoint;
import by.bsuir.mpp.transpony.entity.Type;

import java.util.List;

public class CheckpointService {
    private static final CheckPointDao dao = DaoFactory.getDaoFactory().getCheckPointDao();

    public static List<CheckPoint> getAll() throws ServiceException {
        try {
            return dao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("can't get all check point.", e);
        }
    }
    public static List<CheckPoint> getByRouteId(Integer routeId) throws ServiceException {
        try {
            return dao.getByRouteId(routeId);
        } catch (DaoException e) {
            throw new ServiceException("can't get for route check point.", e);
        }
    }
    public static void updateRouteCheckpoints(List<CheckPoint> checkPoints, Integer routeId) throws ServiceException {
        try {
            dao.updateRouteCheckpoints(checkPoints, routeId);
        } catch (DaoException e) {
            throw new ServiceException("can't update for route.", e);
        }

    }
    public static CheckPoint getById(Integer id) throws ServiceException {
        try {
            return dao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException("can't get for index.", e);
        }
    }

    public static CheckPoint addNew(CheckPoint checkPoint) throws ServiceException {
        try {
            checkPoint.setId(dao.add(checkPoint));
            return checkPoint;
        } catch (DaoException e) {
            throw new ServiceException("can't add new check point.", e);
        }

    }
    public static void delete(CheckPoint checkPoint) throws ServiceException {
        try {
            dao.delete(checkPoint);
        } catch (DaoException e) {
            throw new ServiceException("can't delete this check point.", e);
        }

    }
    public static void update(CheckPoint checkPoint) throws ServiceException {
        try {
            dao.update(checkPoint);
        } catch (DaoException e) {
            throw new ServiceException("can't update this check point.", e);
        }

    }

    public static List<Type> getAllTypes() throws ServiceException {
        try {
            return dao.getAllTypes();
        } catch (DaoException e) {
            throw new ServiceException("can't get all type of check point.", e);
        }
    }
}
