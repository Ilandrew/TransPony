package by.bsuir.mpp.transpony.service;

import by.bsuir.mpp.transpony.entity.DeliveryPoint;
import by.bsuir.mpp.transpony.entity.Receiver;
import by.bsuir.mpp.transpony.util.DatabaseUtils;
import by.bsuir.mpp.transpony.util.DatabaseUtilsTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;
import java.sql.*;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class ReceiverServiceTest {
    private final DatabaseUtils databaseUtils = new DatabaseUtilsTest();


    @Test
    public void getById() {
        try {
            Receiver receiver = ReceiverService.getById(1);
            assertEquals(new Integer(1), receiver.getId());
        } catch (ServiceException e) {
            System.out.println("Receiver.getById fail");
        }
    }

    @Test
    public void getAll() {
        try {
            List<Receiver> collection = ReceiverService.getAll();
            assertNotNull(collection);
        } catch (ServiceException e) {
            System.out.println("Receiver.getAll fail");
        }
    }

    @Test
    public void getByReceiverId() {
        try {
            List<DeliveryPoint> collection = ReceiverService.getByReceiverId(1);
            assertNotNull(collection);
        } catch (ServiceException e) {
            System.out.println("Receiver.getByReceiverId fail");
        }

    }

    @Test
    public void add() {
        Receiver receiver = new Receiver();
        receiver.setName("test1");
        receiver.setPhone("1");
        receiver.setEmail("2");
        receiver.setAddress("12");
        Connection connection = null;
        Statement statement = null;
        Receiver receiverTest = new Receiver();
        try {
            ReceiverService.add(receiver);
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT id_reciever, name, address, phone, email FROM RECIEVER \n" +
                    "WHERE id_reciever in (SELECT max(id_reciever) FROM RECIEVER)");
            if (result.next()) {
                receiverTest.setId(result.getInt("id_reciever"));
                receiverTest.setName(result.getString("name"));
                receiverTest.setAddress(result.getString("address"));
                receiverTest.setPhone(result.getString("phone"));
                receiverTest.setEmail(result.getString("email"));
            }
            ReceiverService.delete(receiverTest);

        } catch (ServiceException|SQLException|NamingException e) {
            System.out.println("Receiver.add fail");
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        assertTrue(receiver.equals(receiverTest));
    }

    @Test
    public void delete() {
        Receiver receiver = new Receiver();
        receiver.setName("test1");
        receiver.setPhone("1");
        receiver.setEmail("2");
        receiver.setAddress("12");
        Connection connection = null;
        PreparedStatement statement = null;
        Receiver receiverTest = new Receiver();
        try {
            ReceiverService.add(receiver);
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement("SELECT id_reciever, name, address, phone, email FROM RECIEVER \n" +
                    "WHERE id_reciever = ?");
            statement.setInt(1, receiver.getId());
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                receiverTest.setId(result.getInt("id_reciever"));
            }
            ReceiverService.delete(receiver);
        } catch (ServiceException|SQLException|NamingException e) {
            System.out.println("Receiver.delete fail");
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    @Test
    public void update() {
        Receiver receiver = new Receiver();
        receiver.setName("test1");
        receiver.setPhone("1");
        receiver.setEmail("2");
        receiver.setAddress("12");
        Receiver receiverTest = new Receiver();
        Connection connection = null;
        Statement statement = null;
        try {
            ReceiverService.add(receiver);
            receiver.setName("test2");
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT max(id_reciever) as id FROM RECIEVER");
            if (result.next()) {
                receiver.setId(result.getInt("id"));
            }
            ReceiverService.update(receiver);
            result = statement.executeQuery("SELECT id_reciever, name, address, phone, email FROM RECIEVER \n" +
                    "WHERE id_reciever in (SELECT max(id_reciever) FROM RECIEVER)");
            if (result.next()) {
                receiverTest.setId(result.getInt("id_reciever"));
                receiverTest.setName(result.getString("name"));
            }
            ReceiverService.delete(receiverTest);

        } catch (ServiceException|SQLException|NamingException e) {
            System.out.println("CheckPointTest.update fail");
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        assertTrue(receiver.equals(receiverTest));
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
