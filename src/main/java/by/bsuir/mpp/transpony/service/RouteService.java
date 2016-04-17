package by.bsuir.mpp.transpony.service;

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
    public static Route getById(Integer id) throws ServiceException {
        try {
            return dao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }

    public static List<CheckPoint> getByRouteId(Integer routeId) throws ServiceException {
        try {
            return dao.getByRouteId(routeId);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }

    public static List<Route> getAll() throws ServiceException {
        try {
            return dao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }

    public static void generateName(Route route) {
        String name = "";
        for (CheckPoint checkPoint: route.getPoints()) {
            name +=checkPoint.getName() + "-";
        }
        if (name.length() != 0) {
            name = name.substring(0, name.length() -1);
        }
        route.setName(name);
    }

}
