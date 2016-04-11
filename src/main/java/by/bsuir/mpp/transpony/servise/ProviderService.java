package by.bsuir.mpp.transpony.servise;

import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.dao.ProviderDao;
import by.bsuir.mpp.transpony.dao.factory.DaoFactory;
import by.bsuir.mpp.transpony.entity.Provider;

import java.util.List;

public class ProviderService {
    private static final ProviderDao dao = DaoFactory.getDaoFactory().getProviderDao();

    public static Provider getForIndex(Integer index) throws ServiceException {
        try {
            return dao.getForIndex(index);
        } catch (DaoException e) {
            throw new ServiceException("can't get for index provider.", e);
        }
    }
    public static List<Provider> getAll() throws ServiceException {
        try {
            return dao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }

    public static Integer add(Provider provider) throws ServiceException {
        try {
            return dao.add(provider);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }
    public static void delete(Provider provider) throws ServiceException {
        try {
            dao.delete(provider);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }
    public static void update(Provider provider) throws ServiceException {
        try {
            dao.update(provider);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }
}
