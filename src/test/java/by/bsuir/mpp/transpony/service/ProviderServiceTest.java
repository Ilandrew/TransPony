package by.bsuir.mpp.transpony.service;

import by.bsuir.mpp.transpony.entity.Car;
import by.bsuir.mpp.transpony.entity.DeliveryPoint;
import by.bsuir.mpp.transpony.entity.Provider;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProviderServiceTest {
    private final DatabaseUtils databaseUtils = new DatabaseUtilsTest();

    @Test
    public void getForIndexTest() {
        try {
            Provider provider = ProviderService.getForIndex(1);
            assertEquals(new Integer(1), provider.getId());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAllTest() {
        try {
            List<Provider> providers = ProviderService.getAll();
            assertTrue(providers.size() > 1);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addTest() {
        Provider provider = new Provider();
        provider.setEmail("add test");
        provider.setAddress("add test");
        provider.setPhone("add test");
        provider.setName(" add test");

        Connection connection = null;
        Statement statement = null;
        Provider providerTest = null;
        try {
            ProviderService.add(provider);
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery("SELECT id_provider as id,\n" +
                    "  address as address,\n" +
                    "  phone as phone,\n" +
                    "  email as email,\n" +
                    "  name as name\n" +
                    "FROM PROVIDER\n" +
                    "WHERE id_provider in (SELECT  max(id_provider) as id FROM PROVIDER)");
            if (result.next()) {
                providerTest = new Provider();
                providerTest.setId(result.getInt("id"));
                providerTest.setAddress(result.getString("address"));
                providerTest.setPhone(result.getString("phone"));
                providerTest.setName(result.getString("name"));
                providerTest.setEmail(result.getString("email"));
            }
            ProviderService.delete(providerTest);
        } catch (ServiceException|SQLException|NamingException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

        assertTrue(provider.equals(provider));

    }

    @Test
    public void deleteTest() {
        Provider provider = new Provider();
        provider.setEmail("del test");
        provider.setAddress("del test");
        provider.setPhone("del test");
        provider.setName("del test");

        Connection connection = null;
        Statement statement = null;
        try {
            ProviderService.add(provider);
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT id_provider as id,\n" +
                    "  address as address,\n" +
                    "  phone as phone,\n" +
                    "  email as email,\n" +
                    "  name as name\n" +
                    "FROM PROVIDER\n" +
                    "WHERE id_provider in (SELECT  max(id_provider) as id FROM PROVIDER)");

            if (result.next()) {
                provider = new Provider();
                provider.setAddress(result.getString("address"));
                provider.setId(result.getInt("id"));

                provider.setPhone(result.getString("phone"));
                provider.setName(result.getString("name"));
                provider.setEmail(result.getString("email"));
            }
            ProviderService.delete(provider);
        } catch (ServiceException | SQLException | NamingException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

    }

    @Test
    public void updateTest() {
        Provider provider = new Provider();
        provider.setEmail("upd test");
        provider.setAddress("upd test");
        provider.setPhone("upd test");
        provider.setName("upd test");

        Connection connection = null;
        Statement statement = null;
        Provider providerTest = null;
        try {
            ProviderService.add(provider);
            provider.setAddress("upd test new");

            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT max(id_provider) as id FROM PROVIDER");
            if (result.next()) {
                Integer i = result.getInt("id");
                provider.setId(i);
            }
            ProviderService.update(provider);
            result = statement.executeQuery("SELECT id_provider as id,\n" +
                    "  address as address,\n" +
                    "  phone as phone,\n" +
                    "  email as email,\n" +
                    "  name as name\n" +
                    "FROM PROVIDER\n" +
                    "WHERE id_provider in (SELECT  max(id_provider) as id FROM PROVIDER)");
            providerTest = new Provider();
            if (result.next()) {

                Integer i = result.getInt("id");
                providerTest.setId(i);
                providerTest.setAddress(result.getString("address"));
                providerTest.setPhone(result.getString("phone"));
                providerTest.setName(result.getString("name"));
                providerTest.setEmail(result.getString("email"));
                providerTest.setAddress(result.getString("address"));
            }
            ProviderService.delete(providerTest);
        } catch (ServiceException | SQLException | NamingException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        assertTrue(provider.equals(providerTest));

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
