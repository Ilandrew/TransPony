package by.bsuir.mpp.transpony.service;
import static org.junit.Assert.*;

import by.bsuir.mpp.transpony.entity.DeliveryPoint;
import by.bsuir.mpp.transpony.util.DatabaseUtils;
import by.bsuir.mpp.transpony.util.DatabaseUtilsTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DeliveryPointTest {
    private final DatabaseUtils databaseUtils = new DatabaseUtilsTest();

    @Test
    public void getForIndexTest() {
        try {
            DeliveryPoint deliveryPoint = DeliveryPointService.getById(1);
            assertEquals(new Integer(1), deliveryPoint.getId());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addTest() {
        DeliveryPoint deliveryPoint = new DeliveryPoint();
        deliveryPoint.setAddress("Test add");
        Connection connection = null;
        Statement statement = null;
        DeliveryPoint deliveryPointTest = new DeliveryPoint();
        try {
            DeliveryPointService.add(deliveryPoint);
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery("SELECT id_delivery_point as id, address as address \n" +
                    "  FROM DELIVERY_POINT\n" +
                    "WHERE id_delivery_point in (SELECT  max(id_delivery_point) as id FROM DELIVERY_POINT)");
            if (result.next()) {
                deliveryPointTest.setId(result.getInt("id"));
                deliveryPointTest.setAddress(result.getString("address"));
            }
            DeliveryPointService.delete(deliveryPointTest);
        } catch (ServiceException|SQLException|NamingException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

        assertTrue(deliveryPoint.equals(deliveryPointTest));
    }

    @Test
    public void deleteTest() {
        DeliveryPoint deliveryPoint = new DeliveryPoint();
        deliveryPoint.setAddress("Test delete");
        Connection connection = null;
        Statement statement = null;
        DeliveryPoint deliveryPointTest = null;
        try {

            DeliveryPointService.add(deliveryPoint);
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT id_delivery_point as id, address as address \n" +
                    "  FROM DELIVERY_POINT\n" +
                    "WHERE id_delivery_point in (SELECT  max(id_delivery_point) as id FROM DELIVERY_POINT)");
            if (result.next()) {
                deliveryPointTest = new DeliveryPoint();
                Integer i = result.getInt("id");
                deliveryPointTest.setId(i);
                deliveryPointTest.setAddress(result.getString("address"));
            }
            DeliveryPointService.delete(deliveryPointTest);
        } catch (ServiceException | SQLException | NamingException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    @Test
    public void updateTest() {
        DeliveryPoint deliveryPoint = new DeliveryPoint();
        deliveryPoint.setAddress("test update");

        Connection connection = null;
        Statement statement = null;
        DeliveryPoint deliveryPointTest = null;
        try {
            DeliveryPointService.add(deliveryPoint);
            deliveryPoint.setAddress("test update new");

            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT max(id_delivery_point) as id FROM DELIVERY_POINT");
            if (result.next()) {
                Integer i = result.getInt("id");
                deliveryPoint.setId(i);
            }
            DeliveryPointService.update(deliveryPoint);
            result = statement.executeQuery("SELECT id_delivery_point as id, address as address \n" +
                    "  FROM DELIVERY_POINT\n" +
                    "WHERE id_delivery_point in (SELECT  max(id_delivery_point) as id FROM DELIVERY_POINT)");
            deliveryPointTest = new DeliveryPoint();
            if (result.next()) {
                Integer i = result.getInt("id");
                deliveryPointTest.setId(i);
                deliveryPointTest.setAddress(result.getString("address"));
            }
            DeliveryPointService.delete(deliveryPointTest);
        } catch (ServiceException | SQLException | NamingException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        assertTrue(deliveryPoint.equals(deliveryPointTest));
    }

    @Test
    public void getAllForReceiver() {
        try {
            List<DeliveryPoint> points = DeliveryPointService.getByReceiverId(1);
            assertTrue(points.size() == 0);
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
        } catch (NamingException|SQLException e) {
            e.printStackTrace();
        }
    }
}
