package by.bsuir.mpp.transpony.servise;

import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.dao.RouteDao;
import by.bsuir.mpp.transpony.dao.factory.DaoFactory;
import by.bsuir.mpp.transpony.entity.CheckPoint;
import by.bsuir.mpp.transpony.entity.Route;

import java.util.List;

public class RouteService {
    private static final RouteDao dao = DaoFactory.getDaoFactory().getRouteDao();

    public static void update(Route route) throws ServiceException {
        try {
            dao.update(route);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }

    }
    public static void add(Route route) throws ServiceException {
        try {
            dao.add(route);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }

    }
    public static void delete(Route route) throws ServiceException {
        try {
            dao.delete(route);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }

    }
    public static Route getForIndex(Integer index) throws ServiceException {
        try {
            return dao.getForIndex(index);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }

    public static List<CheckPoint> getCheckPointForRoute(Integer index) throws ServiceException {
        try {
            return dao.getCheckPointForRoute(index);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }


}
