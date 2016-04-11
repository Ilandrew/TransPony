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
    public ICar getCarDao() {
        return MySqlCar.getInstance();
    }

    @Override
    public ICargo getCargoDao() {
        return MySqlCargo.getInstance();
    }

    @Override
    public ICheckPoint getCheckPointDao() {
        return MySqlCheckPoint.getInstance();
    }

    @Override
    public IDeliveryPoint getDeliveryPointDao() {
        return MySqlDeliveryPoint.getInstance();
    }

    @Override
    public IProvider getProviderDao() {
        return MySqlProvider.getInstance();
    }

    @Override
    public IReceiver getReceiverDao() {
        return MySqlReceiver.getInstance();
    }

    @Override
    public IRoute getRouteDao() {
        return MySqlRoute.getInstance();
    }

    @Override
    public ITrip getTripDao() {
        return MySqlTrip.getInstance();
    }

    @Override
    public IUser getUserDao() {
        return MySqlUser.getInstance();
    }

    @Override
    public IWaybill getWaybillDao() {
        return MySqlWaybill.getInstance();
    }
}
