package by.bsuir.mpp.transpony.dao;

import by.bsuir.mpp.transpony.entity.DeliveryPoint;
import by.bsuir.mpp.transpony.entity.Receiver;

import java.util.List;

public interface DeliveryPointDao {

    DeliveryPoint getForIndex(Integer index) throws DaoException;
    List<DeliveryPoint> getAllForReceiver(Integer index) throws DaoException;

    Integer add(DeliveryPoint deliveryPoint) throws DaoException;
    void update(DeliveryPoint deliveryPoint) throws DaoException;
    void delete(DeliveryPoint deliveryPoint) throws DaoException;

}
