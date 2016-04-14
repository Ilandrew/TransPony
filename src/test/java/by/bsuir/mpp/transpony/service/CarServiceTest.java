package by.bsuir.mpp.transpony.service;

import static org.junit.Assert.*;

import by.bsuir.mpp.transpony.entity.Car;
import by.bsuir.mpp.transpony.util.DatabaseUtils;
import by.bsuir.mpp.transpony.util.DatabaseUtilsTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CarServiceTest {

    private final DatabaseUtils databaseUtils = new DatabaseUtilsTest();

    @Test
    public void getFreeCarTest() {
        try {
            List<Car> cars = CarService.getFreeCar();
            assertTrue(cars.size() > 5);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getForIndexTest() {
        try {
            Car car = CarService.getForIndex(1);
            assertEquals(new Integer(1), car.getId());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addTest() {
        Car car = new Car();
        car.setType("Грузовик");
        car.setModel("Соник");
        car.setVendor("МАЗ");
        car.setFuelConsumption(BigDecimal.valueOf(12.3));
        car.setLicensePlate("1234-kd");
        Connection connection = null;
        Statement statement = null;
        Car carTest = new Car();
        try {
            CarService.addNewCar(car);
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery("SELECT id_car as id, license_plate as license_plate \n" +
                    "  FROM CAR\n" +
                    "WHERE id_car in (SELECT  max(id_car) as id FROM CAR)");
            if (result.next()) {
                carTest.setId(result.getInt("id"));
                carTest.setLicensePlate(result.getString("license_plate"));
            }
            CarService.deleteCar(carTest);
        } catch (ServiceException|SQLException | NamingException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

        assertTrue(car.equals(carTest));
    }

    @Test
    public void deleteTest() {
        Car car = new Car();
        car.setType("Грузовик");
        car.setModel("Соник");
        car.setVendor("МАЗ");
        car.setFuelConsumption(BigDecimal.valueOf(12.3));
        car.setLicensePlate("1234-kd");
        Connection connection = null;
        Statement statement = null;
        Car carTest = null;
        try {
            carTest = new Car();
            CarService.addNewCar(car);
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery("SELECT id_car as id, license_plate as license_plate \n" +
                    "  FROM CAR\n" +
                    "WHERE id_car in (SELECT  max(id_car) as id FROM CAR)");
            if (result.next()) {
                carTest.setId(result.getInt("id"));
                carTest.setLicensePlate(result.getString("license_plate"));
            }
            CarService.deleteCar(carTest);
        } catch (ServiceException|SQLException | NamingException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    @Test
    public void updateTest() {
        Car car = new Car();
        car.setType("Грузовик");
        car.setModel("Соник");
        car.setVendor("МАЗ");
        car.setFuelConsumption(BigDecimal.valueOf(12.3));
        car.setLicensePlate("1234-kd");

        Connection connection = null;
        Statement statement = null;
        Car carTest = null;
        try {
            CarService.addNewCar(car);
            car.setLicensePlate("1235-po");

            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT max(id_car) as id FROM CAR");
            if (result.next()) {
                car.setId(result.getInt("id"));
            }
            CarService.updateCar(car);
            result = statement.executeQuery("SELECT id_car as id, license_plate as license_plate \n" +
                    "  FROM CAR\n" +
                    "WHERE id_car in (SELECT  max(id_car) as id FROM CAR)");
            carTest = new Car();
            if (result.next()) {
                carTest.setId(result.getInt("id"));
                carTest.setLicensePlate(result.getString("license_plate"));
            }
            CarService.deleteCar(carTest);
        } catch (ServiceException|SQLException | NamingException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        assertTrue(car.equals(carTest));
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
