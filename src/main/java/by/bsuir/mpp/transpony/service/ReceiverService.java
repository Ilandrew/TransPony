package by.bsuir.mpp.transpony.service;


import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.dao.ReceiverDao;
import by.bsuir.mpp.transpony.dao.factory.DaoFactory;
import by.bsuir.mpp.transpony.entity.DeliveryPoint;
import by.bsuir.mpp.transpony.entity.Receiver;

import java.util.List;

public class ReceiverService {
    private static final ReceiverDao dao = DaoFactory.getDaoFactory().getReceiverDao();

    public static List<Receiver> getAll() throws ServiceException {
        try {
            return dao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }

    public static Integer add(Receiver receiver) throws ServiceException {
        try {
            return dao.add(receiver);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }

    }
    public static  void delete(Receiver receiver) throws ServiceException {
        try {
            dao.delete(receiver);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }

    }
    public static void update(Receiver receiver) throws ServiceException {
        try {
            dao.update(receiver);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }

    }

    public static List<DeliveryPoint> getByReceiverId(Integer receiverId) throws ServiceException {
        try {
            return dao.getByReceiverId(receiverId);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }

    }

    public static Receiver getById(Integer id) throws ServiceException {
        try {
            return dao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }
}
