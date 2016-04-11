package by.bsuir.mpp.transpony.servise;

import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.dao.WaybillDao;
import by.bsuir.mpp.transpony.dao.factory.DaoFactory;
import by.bsuir.mpp.transpony.entity.Waybill;

import java.util.List;

public class WaybillService {
    private static final WaybillDao dao = DaoFactory.getDaoFactory().getWaybillDao();

    public static List<Waybill> getAll() throws ServiceException {
        try {
            return dao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }

    public static void delete(Waybill waybill) throws ServiceException {
        try {
            dao.delete(waybill);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }
    public static void update(Waybill waybill) throws ServiceException {
        try {
            dao.update(waybill);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }
    public static void add(Waybill waybill) throws ServiceException {
        try {
            dao.add(waybill);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }

    public static Waybill getForIndex(Integer index) throws ServiceException {
        try {
            return dao.getForIndex(index);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }
}
