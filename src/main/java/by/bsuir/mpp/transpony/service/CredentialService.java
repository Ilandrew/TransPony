package by.bsuir.mpp.transpony.service;

import by.bsuir.mpp.transpony.dao.CredentialDao;
import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.dao.factory.DaoFactory;
import by.bsuir.mpp.transpony.entity.User;

public class CredentialService  {
    private static final CredentialDao dao = DaoFactory.getDaoFactory().getCredentialDao();

    public static Integer getId(String login, String pass) throws ServiceException {
        try {
            return dao.getId(login, pass);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }

    public static User getUser(String login, String pass) throws ServiceException {
        try {
            return UserService.getById(dao.getId(login, pass));
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }

    public static Integer getRole(Integer userId) throws ServiceException {
        return UserService.getById(userId).getUserPosition().getId();
    }
}
