package by.bsuir.mpp.transpony.dao;

import by.bsuir.mpp.transpony.entity.DeliveryPoint;

import java.util.List;

public interface DeliveryPointDao {
    DeliveryPoint getById(Integer index) throws DaoException;
    List<DeliveryPoint> getByReceiverId(Integer index) throws DaoException;
    Integer add(DeliveryPoint deliveryPoint) throws DaoException;
    void update(DeliveryPoint deliveryPoint) throws DaoException;
    void delete(DeliveryPoint deliveryPoint) throws DaoException;

}

