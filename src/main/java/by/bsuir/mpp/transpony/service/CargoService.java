package by.bsuir.mpp.transpony.service;

import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.dao.CargoDao;
import by.bsuir.mpp.transpony.dao.factory.DaoFactory;
import by.bsuir.mpp.transpony.entity.Cargo;

import java.util.List;

public class CargoService {

    private static final CargoDao dao = DaoFactory.getDaoFactory().getCargoDao();

    public static List<Cargo> getAll() throws ServiceException {
        try {
            return dao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("can't get all cargo.", e);
        }
    }

    public static Integer add(Cargo cargo) throws ServiceException {
        try {
            return dao.add(cargo);
        } catch (DaoException e) {
            throw new ServiceException("can't add this cargo.", e);
        }
    }
    public static void delete(Cargo cargo) throws ServiceException {
        try {
            dao.delete(cargo);
        } catch (DaoException e) {
            throw new ServiceException("can't delete this cargo.", e);
        }

    }
    public static void update(Cargo cargo) throws ServiceException {
        try {
            dao.update(cargo);
        } catch (DaoException e) {
            throw new ServiceException("can't update this cargo.", e);
        }

    }
    public static Cargo getById(Integer id) throws ServiceException {
        try {
            return dao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException("can't get for id cargo.", e);
        }

    }
    public static List<String> getAllTypes() throws ServiceException {
        try {
            return dao.getAllTypes();
        } catch (DaoException e) {
            throw new ServiceException("can't get all type cargo.", e);
        }

    }
}
