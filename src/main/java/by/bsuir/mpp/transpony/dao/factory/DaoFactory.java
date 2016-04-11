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

    public abstract CarDao getCarDao();
    public abstract CargoDao getCargoDao();
    public abstract CheckPointDao getCheckPointDao();
    public abstract DeliveryPointDao getDeliveryPointDao();
    public abstract ProviderDao getProviderDao();
    public abstract ReceiverDao getReceiverDao();
    public abstract RouteDao getRouteDao();
    public abstract TripDao getTripDao();
    public abstract UserDao getUserDao();
    public abstract WaybillDao getWaybillDao();

}
