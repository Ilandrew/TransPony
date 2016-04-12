package by.bsuir.mpp.transpony.dao;

import by.bsuir.mpp.transpony.entity.User;
import java.util.List;

public interface UserDao {

    List<User> getAll() throws DaoException;
    List<User> getFreeDriver() throws DaoException;
    User getForIndex(Integer index) throws DaoException;

    void fire(User user) throws DaoException;

    void delete(User user) throws DaoException;
    void update(User user) throws DaoException;
    Integer add(User user) throws DaoException;
}
