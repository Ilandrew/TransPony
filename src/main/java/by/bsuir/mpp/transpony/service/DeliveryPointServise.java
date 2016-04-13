package by.bsuir.mpp.transpony.service;

import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.dao.DeliveryPointDao;
import by.bsuir.mpp.transpony.dao.factory.DaoFactory;
import by.bsuir.mpp.transpony.entity.DeliveryPoint;

import java.util.List;

public class DeliveryPointServise {
    private static final DeliveryPointDao dao = DaoFactory.getDaoFactory().getDeliveryPointDao();

    public static DeliveryPoint getForIndex(Integer index) throws ServiceException {
        try {
           return dao.getForIndex(index);
        } catch (DaoException e) {
            throw new ServiceException("can't get for index delivery point.", e);
        }
    }
    public static List<DeliveryPoint> getAllForReceiver(Integer index) throws ServiceException {
        try {
            return dao.getAllForReceiver(index);
        } catch (DaoException e) {
            throw new ServiceException("can't get all for index receiver.", e);
        }

    }

    public static Integer add(DeliveryPoint deliveryPoint) throws ServiceException {
        try {
            return dao.add(deliveryPoint);
        } catch (DaoException e) {
            throw new ServiceException("can't add delivery point.", e);
        }

    }
    public static void update(DeliveryPoint deliveryPoint) throws ServiceException {
        try {
            dao.update(deliveryPoint);
        } catch (DaoException e) {
            throw new ServiceException("can't update delivery point.", e);
        }

    }
    public static void delete(DeliveryPoint deliveryPoint) throws ServiceException {
        try {
            dao.delete(deliveryPoint);
        } catch (DaoException e) {
            throw new ServiceException("can't delete delivery point.", e);
        }

    }


}