package by.bsuir.mpp.transpony.dao;

import by.bsuir.mpp.transpony.entity.DeliveryPoint;
import by.bsuir.mpp.transpony.entity.Receiver;

import java.util.List;

public interface IReceiver {

    List<Receiver> getAll() throws DaoException;

    Integer add(Receiver receiver) throws DaoException;
    void delete(Receiver receiver) throws DaoException;
    void update(Receiver receiver) throws DaoException;

    List<DeliveryPoint> getDeliveryPointForReceiver(Integer index) throws DaoException;

}
