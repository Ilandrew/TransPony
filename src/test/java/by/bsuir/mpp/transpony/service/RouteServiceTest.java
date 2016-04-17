package by.bsuir.mpp.transpony.service;

import static org.junit.Assert.*;

import by.bsuir.mpp.transpony.entity.CheckPoint;
import by.bsuir.mpp.transpony.entity.Route;
import by.bsuir.mpp.transpony.util.DatabaseUtils;
import by.bsuir.mpp.transpony.util.DatabaseUtilsTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteServiceTest  {

    private final DatabaseUtils databaseUtils = new DatabaseUtilsTest();

    @Test
    public void positiveTest() {
        List<CheckPoint> collection = new ArrayList<>();
        CheckPoint cp1 = new CheckPoint();
        cp1.setName("test1");
        collection.add(cp1);
        cp1 = new CheckPoint();
        cp1.setName("test2");
        collection.add(cp1);
        Route route = new Route();
        route.setPoints(collection);
        RouteService.generateName(route);
        assertEquals(route.getName(), "test1-test2");
    }

    @Test
    public void negativeTes() {
        List<CheckPoint> collection = new ArrayList<>();
        Route route = new Route();
        route.setPoints(collection);
        RouteService.generateName(route);
        assertEquals(route.getName(), "");
    }

    @Test
    public void getAllTest() {
        try {
            List<Route> routes = RouteService.getAll();
            assertEquals(routes.size(), 0);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void createConnection() {
        DatabaseUtils.setInstance(databaseUtils);
    }
    @After
    public void closeConnection() {
        try {
            DatabaseUtils.closeConnection(DatabaseUtils.getInstance().getConnection());
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
