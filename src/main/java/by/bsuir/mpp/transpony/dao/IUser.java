package by.bsuir.mpp.transpony.dao;

import by.bsuir.mpp.transpony.entity.User;
import by.bsuir.mpp.transpony.entity.UserCredentials;

import java.util.List;

public interface IUser {

    List<User> getAll() throws DaoException;
    List<User> getFreeDriver() throws DaoException;


    void delete(User user) throws DaoException;
    void update(User user) throws DaoException;
    void add(User user) throws DaoException;
}
