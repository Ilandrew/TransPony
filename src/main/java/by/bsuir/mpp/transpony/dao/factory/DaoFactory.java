package by.bsuir.mpp.transpony.dao.factory;

import by.bsuir.mpp.transpony.dao.*;
import by.bsuir.mpp.transpony.dao.factory.impl.MySqlDaoFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class DaoFactory {

    private final static String DAO_TYPE = readDaoType();

    private static String readDaoType() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("/WEB-INF/props/app.properties"));
            return properties.getProperty("dao.type");
        } catch (IOException e) {
            return "mysql";
        }
    }

    public static DaoFactory getDaoFactory() {
        switch (DAO_TYPE) {
            case "mysql":
            default:
                return MySqlDaoFactory.getInstance();
        }
    }

    public abstract ICar getCarDao();
    public abstract ICargo getCargoDao();
    public abstract ICheckPoint getCheckPointDao();
    public abstract IDeliveryPoint getDeliveryPointDao();
    public abstract IProvider getProviderDao();
    public abstract IReceiver getReceiverDao();
    public abstract IRoute getRouteDao();
    public abstract ITrip getTripDao();
    public abstract IUser getUserDao();
    public abstract IWaybill getWaybillDao();

}
