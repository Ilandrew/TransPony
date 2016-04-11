package by.bsuir.mpp.transpony.dao.factory.impl;

import by.bsuir.mpp.transpony.dao.*;
import by.bsuir.mpp.transpony.dao.factory.DaoFactory;
import by.bsuir.mpp.transpony.dao.impl.mysql.*;

public class MySqlDaoFactory extends DaoFactory{
    private final static DaoFactory instance = new MySqlDaoFactory();
    private MySqlDaoFactory() {}
    public static DaoFactory getInstance() {
        return instance;
    }

    @Override
    public CarDao getCarDao() {
        return MySqlCarDao.getInstance();
    }

    @Override
    public CargoDao getCargoDao() {
        return MySqlCargoDao.getInstance();
    }

    @Override
    public CheckPointDao getCheckPointDao() {
        return MySqlCheckPointDao.getInstance();
    }

    @Override
    public DeliveryPointDao getDeliveryPointDao() {
        return MySqlDeliveryPointDao.getInstance();
    }

    @Override
    public ProviderDao getProviderDao() {
        return MySqlProviderDao.getInstance();
    }

    @Override
    public ReceiverDao getReceiverDao() {
        return MySqlReceiverDao.getInstance();
    }

    @Override
    public RouteDao getRouteDao() {
        return MySqlRouteDao.getInstance();
    }

    @Override
    public TripDao getTripDao() {
        return MySqlTripDao.getInstance();
    }

    @Override
    public UserDao getUserDao() {
        return MySqlUserDao.getInstance();
    }

    @Override
    public WaybillDao getWaybillDao() {
        return MySqlWaybillDao.getInstance();
    }

    @Override
    public CredentialDao getCredentialDao() {
        return null;
    }
}
