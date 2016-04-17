package by.bsuir.mpp.transpony.service;

import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.dao.UserDao;
import by.bsuir.mpp.transpony.dao.factory.DaoFactory;
import by.bsuir.mpp.transpony.entity.User;

import java.util.List;

public class UserService {
    private static final UserDao dao = DaoFactory.getDaoFactory().getUserDao();

    public static List<User> getAll() throws ServiceException {
        try {
            return dao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }
    public static List<User> getFreeDrivers() throws ServiceException {
        try {
            return dao.getFreeDrivers();
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }
    public static User getById(Integer id) throws ServiceException {
        try {
            return dao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }

    public static void fire(User user) throws ServiceException {
        try {
            dao.fire(user);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }

    public static void delete(User user) throws ServiceException {
        try {
            dao.delete(user);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }
    public static void update(User user) throws ServiceException {
        try {
            dao.update(user);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }
    public static Integer add(User user) throws ServiceException {
        try {
            return dao.add(user);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }
}
