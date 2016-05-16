package by.bsuir.mpp.transpony.service;

import by.bsuir.mpp.transpony.entity.CheckPoint;
import by.bsuir.mpp.transpony.entity.Type;
import by.bsuir.mpp.transpony.util.DatabaseUtils;
import by.bsuir.mpp.transpony.util.DatabaseUtilsTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;
import java.sql.*;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;


public class CheckpointServicetest {

    private final DatabaseUtils databaseUtils = new DatabaseUtilsTest();

    @Test
    public void getAll() {
        try {
            List<CheckPoint> collection = CheckpointService.getAll();
            assertNotNull(collection);
        } catch (ServiceException e) {
            System.out.println("CheckPointTest.getAll fail");
        }
    }

    @Test
    public void getByRouteId() {
        try {
            List<CheckPoint> collection = CheckpointService.getByRouteId(1);
            assertNotNull(collection);
        } catch (ServiceException e) {
            System.out.println("CheckPointTest.getByRouteId fail");
        }
    }

    @Test
    public void getById() {
        try {
            CheckPoint checkPoint = CheckpointService.getById(1);
            assertEquals(new Integer(1), checkPoint.getId());
        } catch (ServiceException e) {
            System.out.println("CheckPointTest.getById fail");
        }
    }

    @Test
    public void addNew() {
        CheckPoint checkPoint = new CheckPoint();
        checkPoint.setName("test1");
        checkPoint.setPointType("Проезд");
        checkPoint.setX(new Float(1));
        checkPoint.setY(new Float(2));
        Connection connection = null;
        Statement statement = null;
        CheckPoint checkPointTest = new CheckPoint();
        try {
            CheckpointService.addNew(checkPoint);
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT cp.id_check_point as id, cp.name as name, ct.name as type FROM CHECK_POINT cp\n" +
                    "LEFT JOIN CHECK_POINT_TYPE ct ON cp.id_check_point_type = ct.id_check_point_type\n" +
                    "WHERE id_check_point in (SELECT max(id_check_point) FROM CHECK_POINT)");
            if (result.next()) {
                checkPointTest.setId(result.getInt("id"));
                checkPointTest.setName(result.getString("name"));
                checkPointTest.setPointType(result.getString("type"));
            }
            CheckpointService.delete(checkPointTest);

        } catch (ServiceException|SQLException|NamingException e) {
            System.out.println("CheckPointTest.addNew fail");
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        assertTrue(checkPoint.equals(checkPointTest));
    }

    @Test
    public void delete() {
        CheckPoint checkPoint = new CheckPoint();
        checkPoint.setName("test1");
        checkPoint.setPointType("Проезд");
        checkPoint.setX(new Float(1));
        checkPoint.setY(new Float(2));
        Connection connection = null;
        PreparedStatement statement = null;
        CheckPoint checkPointTest = new CheckPoint();
        try {
            CheckpointService.addNew(checkPoint);
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement("SELECT cp.id_check_point as id, cp.name as name, ct.name as type FROM CHECK_POINT cp\n" +
                    "LEFT JOIN CHECK_POINT_TYPE ct ON cp.id_check_point_type = ct.id_check_point_type\n" +
                    "WHERE id_check_point = ?");
            statement.setInt(1, checkPoint.getId());
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                checkPointTest.setId(result.getInt("id"));
                checkPointTest.setName(result.getString("name"));
                checkPointTest.setPointType(result.getString("type"));
            }
            CheckpointService.delete(checkPointTest);
        } catch (ServiceException|SQLException|NamingException e) {
            System.out.println("CheckPointTest.delete fail");
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    @Test
    public void update() {
        CheckPoint checkPoint = new CheckPoint();
        checkPoint.setName("test1");
        checkPoint.setPointType("Проезд");
        checkPoint.setX(new Float(1));
        checkPoint.setY(new Float(2));
        Connection connection = null;
        Statement statement = null;
        CheckPoint checkPointTest = new CheckPoint();
        try {
            CheckpointService.addNew(checkPoint);
            checkPoint.setName("test2");
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT max(id_check_point) as id FROM CHECK_POINT");
            if (result.next()) {
                checkPoint.setId(result.getInt("id"));
            }
            CheckpointService.update(checkPoint);
            result = statement.executeQuery("SELECT cp.id_check_point as id, cp.name as name, ct.name as type FROM CHECK_POINT cp\n" +
                    "LEFT JOIN CHECK_POINT_TYPE ct ON cp.id_check_point_type = ct.id_check_point_type\n" +
                    "WHERE id_check_point in (SELECT max(id_check_point) FROM CHECK_POINT)");
            if (result.next()) {
                checkPointTest.setId(result.getInt("id"));
                checkPointTest.setName(result.getString("name"));
                checkPointTest.setPointType(result.getString("type"));
            }
            CheckpointService.delete(checkPointTest);

        } catch (ServiceException|SQLException|NamingException e) {
            System.out.println("CheckPointTest.update fail");
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        assertTrue(checkPoint.equals(checkPointTest));
    }

    @Test
    public void getAllTypes() {
        try {
            List<Type> collection = CheckpointService.getAllTypes();
            assertNotNull(collection);
        } catch (ServiceException e) {
            System.out.println("CheckPointTest.getAll fail");
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
